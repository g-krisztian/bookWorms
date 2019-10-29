(ns frontend.books
  (:require [clj-http.client :as client]
            [hiccup.table]
            [hiccup.core :as markup]
            [clojure.string :as str]
            [clojure.data.json :as json]))

(defn getbooks []
  (let [
        page (slurp "resources/templates/main.html")
        navbar (slurp "resources/templates/navbar.html")

        value (client/get "http://localhost:8080/customer/books" {:accept :json})
        books (json/read-str (:body value) :key-fn keyword)
        bookswithlinks (map #(assoc %
                               :edit (str  "<a class=\"btn btn-default btn-xs\" href=\"/librarian/book/" (:id %) "\" role=\"button\">EDIT</a>")
                               :action (str  "<a class=\"btn btn-default btn-xs\" href=\"/librarian/book/" (:id %) "\" role=\"button\">ACTION</a>"))
                            books)
        attr-fns {:table-attrs          {:class "table table-hover"}
                  :th-attrs             (fn [label-key _] {:class (name label-key)})
                  :data-value-transform (fn [label-key val]
                                          (case label-key
                                            :id [:a {:href (str "/librarian/book/" val)} val]
                                            val)
                                          )}
        table (hiccup.table/to-table1d bookswithlinks [:id "id" :author "Author" :title "title" :genres "Genre" :printType "type" :available "available" :edit "" :action ""] attr-fns)
        ]
    (str (str/replace page "<!-- insert navbar here -->" navbar) (markup/html table))))
