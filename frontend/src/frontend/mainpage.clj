(ns frontend.mainpage
  (:require [clojure.string :as str]))
(defn mainpage []
  (let [page (slurp "resources/templates/main.html")
        navbar (slurp "resources/templates/navbar.html")]
    (str/replace page "<!-- insert navbar here -->" navbar)))