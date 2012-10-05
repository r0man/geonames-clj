(ns geonames.test.countries
  (:use clojure.test
        geonames.countries))

(deftest test-countries
  (let [countries (countries)]
    (is (= 252 (count countries)))
    (let [country (first countries)]
      (is (= 3041565 (:id country)))
      (is (= "Andorra" (:name country)))
      (is (= #{"ca"} (:languages country)))
      (is (= #{"FR" "ES"} (:neighbours country))))))