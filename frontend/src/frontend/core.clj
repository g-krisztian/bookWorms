(ns frontend.core
  (:gen-class)
  (:require
    [compojure.core :refer [defroutes GET POST]]
    [ring.adapter.jetty :as jetty]
    [clojure.data.json :as json]
    [frontend.books :as books]
    [frontend.customers :as customers]))

(defroutes frontend
           (GET "/books" []  (books/getbooks))
           (GET "/customers" [] (customers/getall))
           (GET "/"  [] (slurp "resources/templates/bootstrap.html")))

(defn -main []
  (jetty/run-jetty frontend {:port 8085}))