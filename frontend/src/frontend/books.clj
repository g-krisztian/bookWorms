(ns frontend.books
  (:require [clj-http.client :as client]))

(defn getbooks []
  (client/get "http://localhost:8080/customer/books" {:accept :json}))
