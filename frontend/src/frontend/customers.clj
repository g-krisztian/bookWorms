(ns frontend.customers
  (:require [clj-http.client :as client]
            [clojure.string :as str]
            [hiccup.table]
            [hiccup.core :as markup]
            [clojure.data.json :as json]))

(defn getall []
  (let
    [page (slurp "resources/templates/main.html")
     navbar (slurp "resources/templates/navbar.html")
     value (client/get "http://localhost:8080/librarian/getUsers" {:accept :json})
     customers (map #(% :userData) (json/read-str (:body value) :key-fn keyword))

     attr-fns {:table-attrs          {:class "table table-hover" :style "max-width:500px; margin-left:7%" }
               :th-attrs             (fn [label-key _] {:class (name label-key)})
               ;:data-tr-attrs        {:onclick (format "window.location='/librarian/customer/%s'"  (get customers ) :id)}

               :data-value-transform (fn [label-key val]
                                       (if (= :id label-key)
                                         [:a {:href (str "/librarian/customer/" val)} val]
                                         val)) }
     table (hiccup.table/to-table1d customers [:id "id" :fullName "Name" :email "e-mail"] attr-fns)
     ]
    (str/replace page "<!-- insert navbar here -->" (str navbar (markup/html table)))
  )
)

(defn createForm []
  (let [page (slurp "resources/templates/createCustomer.html")
        navbar (slurp "resources/templates/navbar.html")]
    (str/replace page "<!-- insert navbar here -->" navbar))
  )

(defn postCustomer [fullname email]
  (clj-http.client/post "http://localhost:8080/librarian/createCustomer" {:content-type :json :debug true :debug-body true :body (json/write-str {:fullName fullname :email email})})
  (getall)
  )
