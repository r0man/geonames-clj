(ns geonames.test.continents
  (:use clojure.test geonames.continents))

(deftest test-find-by-iso-3166-alpha-2
  (are [code] (is (= (:iso-3166-alpha-2 (find-by-iso-3166-alpha-2 code)) code))
       "AF" "AN" "AS" "EU" "NA" "OC" "SA"))

(deftest test-europe
  (let [continent (find-by-iso-3166-alpha-2 "EU")]
    (are [attribute value] (= (attribute continent) value)
         :name "Europe"
         :iso-3166-alpha-2 "EU"
         :geonames-id 6255148)))

(deftest test-south-america
  (let [continent (find-by-iso-3166-alpha-2 "SA")]
    (are [attribute value] (= (attribute continent) value)
         :name "South America"
         :iso-3166-alpha-2 "SA"
         :geonames-id 6255150)))
