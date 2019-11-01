(ns frontend.borrow
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]
            [hiccup.core :as markup]
            [clojure.string :as str]))

(defn pending []
  (let [
        page (slurp "resources/templates/main.html")
        navbar (slurp "resources/templates/navbar.html")

        value (client/get "http://localhost:8080/librarian/pendingBorrows" {:accept :json})
        borrows (json/read-str (:body value) :key-fn keyword)
        ]
    (str (str/replace page "<!-- insert navbar here -->" navbar) (markup/html table))))

    )
