(ns geonames.continents-test
  (:require [clojure.test :refer :all]
            [geonames.continents :as continents]))

(deftest test-continent-by-code
  (is (= {:id 6255148 :code "EU" :name "Europe"}
         (continents/continent-by-code "EU")
         (continents/continent-by-code "eu"))))

(deftest test-continent-by-name
  (is (= {:id 6255148 :code "EU" :name "Europe"}
         (continents/continent-by-name "Europe")
         (continents/continent-by-name "europe"))))

(deftest test-continent-by-id
  (is (= {:id 6255148 :code "EU" :name "Europe"}
         (continents/continent-by-id 6255148))))
