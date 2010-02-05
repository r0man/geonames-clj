(ns geonames.test.country-info
  (:use clojure.test geonames.country-info))

(def *line* "FO\tFRO\t234\tFO\tFaroe Islands\tTórshavn\t1399\t48228\tEU\t.fo\tDKK\tKrone\t298\tFO-###\t^(?:FO)*(\\d{3})$\tfo,da-FO\t2622320")

(deftest test-comment?
  (is (comment? "# comment"))
  (is (not (comment? *line*))))

(deftest test-parse-country
  (let [country (parse-country *line*)]
    (are [attribute expected] (= (country attribute) expected)
    :area 1399
    :capital "Tórshavn"
    :continent-code "EU"
    :currency-code "DKK"
    :currency-name "Krone"
    :fips-code "FO"
    :geoname-id 2622320
    :iso-3166-alpha-2 "FO"
    :iso-3166-alpha-3 "FRO"
    :iso-3166-numeric 234
    :languages ["fo" "da-FO"]
    :name "Faroe Islands"
    :phone-prefix "298"
    :post-code-format "FO-###"
    :post-code-regexp "^(?:FO)*(\\d{3})$"
    :population 48228
    :top-level-domain ".fo"
    )))

(deftest test-parse-countries
  (let [countries (parse-countries)]
    (is (= (count countries) 248))))

