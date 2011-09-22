(ns geonames.test.regions
  (:use clojure.test geonames.regions))

(deftest test-parse-region
  (is (nil? (parse-region "invalid")))
  (let [region (parse-region "AD.00\tAndorra (general)")]
    (are [keyword expected]
      (= expected (get region keyword))
      :name "Andorra (general)"
      :country-id "AD"
      :region-id "00"
      :geonames-id "AD.00"))
  (let [region (parse-region "CH.BL\tBasel-Landschaft")]
    (are [keyword expected]
      (= expected (get region keyword))
      :name "Basel-Landschaft"
      :country-id "CH"
      :region-id "BL"
      :geonames-id "CH.BL"))
  (let [region (parse-region "GR.ESYE11\tEast Macedonia and Thrace")]
    (are [keyword expected]
      (= expected (get region keyword))
      :name "East Macedonia and Thrace"
      :country-id "GR"
      :region-id "ESYE11"
      :geonames-id "GR.ESYE11")))

(deftest test-parse-regions
  (let [regions (parse-regions)]
    (is (> (count regions) 3800))))
