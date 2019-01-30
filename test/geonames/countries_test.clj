(ns geonames.countries-test
  (:require [clojure.test :refer :all]
            [geonames.countries :as countries]))

(deftest test-countries
  (let [countries (countries/countries)]
    (is (= 252 (count countries)))
    (let [country (first countries)]
      (is (= 3041565 (:id country)))
      (is (= "Andorra" (:name country)))
      (is (= #{"ca"} (:languages country)))
      (is (= #{"FR" "ES"} (:neighbours country)))
      (is (= "376" (:phone-prefix country))))
    (is (every? #(not (= "" %1)) (map :phone-prefix countries)))))
