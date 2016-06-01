(defproject geonames "0.6.8"
  :description "Clojure API for GeoNames."
  :url "https://github.com/r0man/geonames-clj"
  :min-lein-version "2.0.0"
  :author "r0man"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :lein-release {:deploy-via :clojars}
  :dependencies [[clj-http "3.1.0"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]])
