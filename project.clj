(defproject geonames "0.6.7-SNAPSHOT"
  :description "Clojure API for GeoNames."
  :url "https://github.com/r0man/geonames-clj"
  :min-lein-version "2.0.0"
  :author "Roman Scherer"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :lein-release {:deploy-via :clojars}
  :dependencies [[clj-http "0.7.7"]
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/data.json "0.2.3"]])
