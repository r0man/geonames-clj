(ns geonames.test.util
  (:use clojure.test
        geonames.util))

(deftest test-url-encode
  (are [string encoding expected]
    (is (= (url-encode string encoding) expected))
    "" "UTF-8" ""
    "Berlin" "UTF-8" "Berlin"
    "13.41,52.52" "UTF-8" "13.41%2C52.52"
    :keyword "UTF-8" "keyword"
    {:center "52.523,13.411"} "UTF-8" "center=52.523%2C13.411"
    {:center "52.523,13.411" :sensor false} "UTF-8" "center=52.523%2C13.411&sensor=false"))
