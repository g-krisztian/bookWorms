(ns frontend.customers
  (:require [clj-http.client :as client]
            [clojure.string :as str]))

(defn getall []
  (client/get "http://localhost:8080/librarian/getUsers" {:accept :json}))

(defn createForm []
  (let [page (slurp "resources/templates/createCustomer.html")
        navbar (slurp "resources/templates/navbar.html")]
    (str/replace page "<!-- insert navbar here -->" navbar))
  )
