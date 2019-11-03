(ns frontend.borrow
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]
            [hiccup.core :as markup]
            [clojure.string :as str]
            [frontend.mainpage :as mainpage]))

(def page (slurp "resources/templates/main.html"))
(def navbar (slurp "resources/templates/navbar.html"))
(defn get [url] (client/get url {:accept :json}))
(defn get-body [value] (json/read-str (:body value) :key-fn keyword))

(defn get-borrows [url] (->
                          url
                          get
                          get-body))

(defn pending []
  (let [borrows (get-borrows "http://localhost:8080/librarian/getPendingBorrows")
        borrowstable (map #(assoc {}
                             :customer (get-in % [:customer :userData :fullName])
                             :book (str (get-in % [:book :author]) ": " (get-in % [:book :title]))
                             :activate (str "<a class=\"btn btn-default btn-xs\" href=\"/activateBorrow/" (:id %) "/pending\" role=\"button\">Activate</a>")
                             :close (str "<a class=\"btn btn-default btn-xs\" href=\"/closeBorrow/" (:id %) "/pending\" role=\"button\">Close</a>")
                             )
                          borrows)
        attr-fns {:table-attrs {:class "table table-hover" :style "width:50%;margin-left:7%; border: 1px solid lightgray;"}}
        table (hiccup.table/to-table1d borrowstable [:book "Book" :customer "Customer" :activate "" :close ""] attr-fns)]
    (str (str/replace page "<!-- insert navbar here -->" navbar) "<h1>Pending borrows</h1>" (markup/html table)))
  )

(defn active []
  (let [
        borrows (get-borrows "http://localhost:8080/librarian/getActiveBorrows")
        borrowstable (map #(assoc {}
                             :customer (get-in % [:customer :userData :fullName])
                             :book (str (get-in % [:book :author]) ": " (get-in % [:book :title]))
                             :action (str "<a class=\"btn btn-default btn-xs\" href=\"/closeBorrow/" (:id %) "/active\" role=\"button\">Close</a>"))
                          borrows)
        attr-fns {:table-attrs {:class "table table-hover" :style "width:50%;margin-left:7%; border: 1px solid lightgray;"}}
        table (hiccup.table/to-table1d borrowstable [:book "Book" :customer "Customer" :action ""] attr-fns)]
    (str (str/replace page "<!-- insert navbar here -->" navbar) "<h1>Active borrows</h1>" (markup/html table)))
  )

(defn returning []
  (let [borrows (get-borrows "http://localhost:8080/librarian/getReturningBorrows")
        borrowstable (map #(assoc {}
                             :customer (get-in % [:customer :userData :fullName])
                             :book (str (get-in % [:book :author]) ": " (get-in % [:book :title]))
                             :action (str "<a class=\"btn btn-default btn-xs\" href=\"/closeBorrow/" (:id %) "/returning\" role=\"button\">Close</a>"))
                          borrows)
        attr-fns {:table-attrs {:class "table table-hover" :style "width:50%;margin-left:7%; border: 1px solid lightgray;"}}
        table (hiccup.table/to-table1d borrowstable [:book "Book" :customer "Customer" :action ""] attr-fns)]
    (str (str/replace page "<!-- insert navbar here -->" navbar) "<h1>Returning borrows</h1>" (markup/html table)))
  )

(defn closed []
  (let [borrows (get-borrows "http://localhost:8080/librarian/getClosedBorrows")
        borrowstable (map #(assoc {}
                             :customer (get-in % [:customer :userData :fullName])
                             :book (str (get-in % [:book :author]) ": " (get-in % [:book :title])))
                          borrows)
        attr-fns {:table-attrs {:class "table table-hover" :style "width:50%;margin-left:7%; border: 1px solid lightgray;"}}
        table (hiccup.table/to-table1d borrowstable [:book "Book" :customer "Customer" :action ""] attr-fns)]
    (str (str/replace page "<!-- insert navbar here -->" navbar) "<h1>Closed borrows</h1>" (markup/html table)))
  )

(defn close [id redirect]
  (client/put (str "http://localhost:8080/librarian/closeBorrow/" id))
  (case redirect
    "pending" (pending)
    "active" (active)
    (mainpage/mainpage)
    )
  )

(defn activate [id redirect]
  (client/put (str "http://localhost:8080/librarian/activateBorrow/" id))
  (case redirect
    "pending" (pending)
    "active" (active)
    (mainpage/mainpage)
    )
  )