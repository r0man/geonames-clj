(ns geonames.countries-test
  (:require [clojure.string :refer [blank?]])
  (:use clojure.test
        geonames.cities))

(deftest test-cities
  (let [cities (cities-15000)]
    (is (> 23000 (count cities))) ; was 23322 on 2014-04-21T1043
    (let [san-francisco (first (filter #(= 5391959 (:geoname-id %)) cities))]
      (is (= 5391959 (:geoname-id san-francisco)))
      (is (= "San Francisco" (:name san-francisco)))
      (is (= "US" (:country-code san-francisco)))
      (is (= "CA" (:admin1-code san-francisco)))
      (is (= "America/Los_Angeles" (:timezone san-francisco))))))
