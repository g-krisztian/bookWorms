(defproject frontend "0.1.0-SNAPSHOT"
  :description "frontend part of library application"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.7.0"]
                 [compojure "1.6.1"]
                 [clj-http "3.10.0"]
                 [hiccup-table "0.2.0"]
                 [org.clojure/data.json "0.2.6"]]

  :main ^:skip-aot frontend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
