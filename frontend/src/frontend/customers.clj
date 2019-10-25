(ns frontend.customers
  (:require [clj-http.client :as client]))

(defn getall []
  (client/get "http://localhost:8080/librarian/getUsers" {:accept :json}))
