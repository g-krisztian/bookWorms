(ns frontend.core
  (:gen-class)
  (:require
    [compojure.core :refer [defroutes GET POST]]
    [ring.adapter.jetty :as jetty]
    [clj-http.client :as client]
    [clojure.data.json :as json]))

(defroutes frontend
           (GET "/books" []   (client/get "http://localhost:8080/customer/books" {:accept :json})))

(defn -main []
  (jetty/run-jetty frontend {:port 8085}))