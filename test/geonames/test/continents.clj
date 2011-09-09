(ns geonames.test.continents
  (:use [clojure.string :only (lower-case)]
        clojure.test
        geonames.continents)
  (:import geonames.continents.Continent))

(deftest test-find-by-iso-3166-1-alpha-2
  (are [code] (is (= (:iso-3166-1-alpha-2 (find-by-iso-3166-1-alpha-2 code))
                     (lower-case code)))
       "af" "an" "as" "eu" "na" "oc" "sa"
       "AF" "AN" "AS" "EU" "NA" "OC" "SA"))

(deftest test-make-continent
  (is (= (make-continent "Europe" "eu" 6255148)
         (Continent. "Europe" "eu" 6255148))))

(deftest test-europe
  (let [continent (find-by-iso-3166-1-alpha-2 "EU")]
    (are [attribute value] (= (attribute continent) value)
         :name "Europe"
         :iso-3166-1-alpha-2 "eu"
         :geonames-id 6255148)))

(deftest test-south-america
  (let [continent (find-by-iso-3166-1-alpha-2 "SA")]
    (are [attribute value] (= (attribute continent) value)
         :name "South America"
         :iso-3166-1-alpha-2 "sa"
         :geonames-id 6255150)))
