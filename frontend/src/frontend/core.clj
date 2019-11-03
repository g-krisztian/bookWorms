(ns frontend.core
  (:gen-class)
  (:require
    [compojure.core :refer [defroutes GET POST]]
    [ring.adapter.jetty :as jetty]
    [ring.middleware.params :as params]
    [clojure.data.json :as json]
    [frontend.books :as books]
    [frontend.borrow :as borrow]
    [frontend.customers :as customers]
    [frontend.mainpage :as mainpage]))


(defroutes frontend
           (GET "/" [] (mainpage/mainpage))
           (GET "/favicon.ico" [] (slurp "resources/static/favicon.ico"))
           (GET "/books" [] (books/getbooks))
           (GET "/customers" [] (customers/getall))
           (GET "/createCustomer" [] (customers/createForm))
           (POST "/createCustomer" [fullname email] (customers/postCustomer fullname email))
           (GET "/createBook" [] (books/createForm))
           (POST "/createBook" [author title genre printType copies] (books/postBook author title genre printType copies))
           (GET "/getPendingBorrows" [] (borrow/pending))
           (GET "/activateBorrow/:id/:redirect" [id redirect] (str "activate: " id " " redirect))
           (GET "/getActiveBorrows" [] (borrow/active))
           (GET "/closeBorrow/:id/:redirect" [id redirect] (str "close: " id " " redirect))
           (GET "/getReturningBorrows" [] (borrow/returning))
           (GET "/closeBorrow/:id/:redirect" [id redirect] (str "close: " id " " redirect))
           (GET "/getClosedBorrows" [] (borrow/closed))
           )

(def app (params/wrap-params frontend))

(defn -main []
  (jetty/run-jetty app {:port 8085}))