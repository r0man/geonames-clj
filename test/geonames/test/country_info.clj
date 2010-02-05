(ns geonames.test.country-info
  (:use clojure.test geonames.country-info))

(def *country-line* "FO\tFRO\t234\tFO\tFaroe Islands\tTórshavn\t1399\t48228\tEU\t.fo\tDKK\tKrone\t298\tFO-###\t^(?:FO)*(\\d{3})$\tfo,da-FO\t2622320")

(deftest test-comment?
  (is (comment? "# comment"))
  (is (not (comment? *country-line*))))

(deftest test-parse-country
  (let [country (parse-country *country-line*)]
    (is (= (:alpha-2 (:iso-3166 country)) "FO"))
    (is (= (:alpha-3 (:iso-3166 country)) "FRO"))
    (is (= (:numeric (:iso-3166 country)) 234))
    (is (= (:name country) "Faroe Islands"))
    (is (= (:capital country) "Tórshavn"))
    (is (= (:area country) 1399))
    (is (= (:population country) 48228))
    (is (= (:code (:continent country) "EU")))
    (is (= (:top-level-domain country) ".fo"))
    (is (= (:code (:currency country) "DKK")))
    (is (= (:name (:currency country) "Krone")))
    (is (= (:phone country) "298"))))

(deftest test-parse-countries
  (let [countries (parse-countries)]
    (is (= (count countries) 248))))

