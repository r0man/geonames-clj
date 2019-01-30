(defproject geonames "0.7.0"
  :description "Clojure API for GeoNames."
  :url "https://github.com/r0man/geonames-clj"
  :min-lein-version "2.0.0"
  :author "r0man"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :lein-release {:deploy-via :clojars}
  :dependencies [[clj-http "3.9.1"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/data.json "0.2.6"]]
  :plugins [[jonase/eastwood "0.3.5"]]
  :aliases {"ci" ["do" ["test"] ["lint"]]
            "lint" ["do"  ["eastwood"]]})
