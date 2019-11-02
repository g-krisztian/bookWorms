(ns frontend.borrow
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]
            [hiccup.core :as markup]
            [clojure.string :as str]))


(defn create-table [borrows redirect action ]
  (let [borrowstable (map #(assoc {}
                       :customer (get-in % [:customer :userData :fullName] )
                       :book (str (get-in % [:book :author]) ": " (get-in % [:book :title]))
                       :action (str "<a class=\"btn btn-default btn-xs\" href=\"/activateBorrow/" (:id %) redirect "\" role=\"button\">" action "</a>") )
                    borrows)
  attr-fns {:table-attrs          {:class "table table-hover" :style "width:50%;margin-left:7%; border: 1px solid lightgray;"}
            ;:th-attrs             (fn [label-key _] {:class (name label-key)})
            ;:data-value-transform (fn [label-key val]
            ;                        (case label-key
            ;                          :id [:a {:href (str "/librarian/book/" val)} val]
            ;                          val)
            ;                        )
            }
  table (hiccup.table/to-table1d borrowstable [:book "Book" :customer "Customer" :action ""] attr-fns)]
    table)
  )


(defn pending []
  (let [
        page (slurp "resources/templates/main.html")
        navbar (slurp "resources/templates/navbar.html")

        value (client/get "http://localhost:8080/librarian/getPendingBorrows" {:accept :json})
        borrows (json/read-str (:body value) :key-fn keyword)
        redirect "/pending"
        action "Activate"
        table (create-table borrows redirect action)
        ]
    (str (str/replace page "<!-- insert navbar here -->" navbar) (markup/html table)))

    )
