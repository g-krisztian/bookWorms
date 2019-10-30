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
     customers (map #(assoc (:userData %) :active (:isActive %)) (json/read-str (:body value) :key-fn keyword))



     attr-fns {:table-attrs          {:class "table table-hover" :style "max-width:500px; margin-left:7%; border: 1px solid lightgray;" }
               :th-attrs             (fn [label-key _] {:class (name label-key)})
               :data-value-transform (fn [label-key val]
                                       (case label-key
                                         :email (str  " <a href=\"mailto:" val "\">"val "</a>")
                                         :active (if val "active" "Not active")
                                         val)) }
     table (hiccup.table/to-table1d customers [:fullName "Name" :email "e-mail" :active "active"] attr-fns)
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
