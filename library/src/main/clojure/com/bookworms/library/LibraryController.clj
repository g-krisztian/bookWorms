(ns com.bookworms.library.LibraryController
  (:import org.springframework.stereotype.Controller
            (org.springframework.web.bind.annotation RequestMapping RequestMethod ResponseBody))
  (:gen-class
    :name ^{Controller ""} com.bookworms.library.controller
    :methods [[^{RequestMapping {:value ["/library"] :method [RequestMethod/GET]} ResponseBody {}} hello [] String]]))

(defn -hello [this]
  "Hello world from a Clojure-based controller!")