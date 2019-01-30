(ns geonames.cities-test
  (:require [clojure.test :refer :all]
            [geonames.cities :as cities]))

(deftest test-cities
  (let [cities (cities/cities-15000)]
    (is (not (empty? cities)))
    (let [san-francisco (first (filter #(= 5391959 (:geoname-id %)) cities))]
      (is (= 5391959 (:geoname-id san-francisco)))
      (is (= "San Francisco" (:name san-francisco)))
      (is (= "-122.4" (format "%.1f" (:longitude san-francisco))))
      (is (= "37.8" (format "%.1f" (:latitude san-francisco))))
      (is (= "US" (:country-code san-francisco)))
      (is (= "CA" (:admin1-code san-francisco)))
      (is (= "America/Los_Angeles" (:timezone san-francisco))))))
