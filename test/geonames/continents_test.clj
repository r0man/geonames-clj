(ns geonames.continents-test
  (:use clojure.test
        geonames.continents))

(deftest test-continent-by-code
  (is (= {:id 6255148 :code "EU" :name "Europe"}
         (continent-by-code "EU")
         (continent-by-code "eu"))))

(deftest test-continent-by-name
  (is (= {:id 6255148 :code "EU" :name "Europe"}
         (continent-by-name "Europe")
         (continent-by-name "europe"))))

(deftest test-continent-by-id
  (is (= {:id 6255148 :code "EU" :name "Europe"}
         (continent-by-id 6255148))))
