(ns geonames.test.countries
  (:use clojure.test geonames.countries))

(deftest test-parse-faroe-islands
  (let [country (parse-country "FO\tFRO\t234\tFO\tFaroe Islands\tTórshavn\t1399\t48228\tEU\t.fo\tDKK\tKrone\t298\tFO-###\t^(?:FO)*(\\d{3})$\tfo,da-FO\t2622320")]
    (are [attribute expected] (= (country attribute) expected)
         :area 1399
         :capital "Tórshavn"
         :continent-code "EU"
         :currency-code "DKK"
         :currency-name "Krone"
         :fips-code "FO"
         :geonames-id 2622320
         :iso-3166-alpha-2 "FO"
         :iso-3166-alpha-3 "FRO"
         :iso-3166-numeric 234
         :languages ["fo" "da-FO"]
         :name "Faroe Islands"
         :neighbours nil
         :phone-prefix "298"
         :population 48228
         :post-code-format "FO-###"
         :post-code-regexp "^(?:FO)*(\\d{3})$"
         :top-level-domain ".fo")))

(deftest test-parse-germany
  (let [country (parse-country "DE\tDEU\t276\tGM\tGermany\tBerlin\t357021\t82369000\tEU\t.de\tEUR\tEuro\t49\t#####\t^(\\d{5})$\tde\t2921044\tCH,PL,NL,DK,BE,CZ,LU,FR,AT")]
    (are [attribute expected] (= (country attribute) expected)
         :area 357021
         :capital "Berlin"
         :continent-code "EU"
         :currency-code "EUR"
         :currency-name "Euro"
         :fips-code "GM"
         :geonames-id 2921044
         :iso-3166-alpha-2 "DE"
         :iso-3166-alpha-3 "DEU"
         :iso-3166-numeric 276
         :languages ["de"]
         :name "Germany"
         :neighbours ["CH" "PL" "NL" "DK" "BE" "CZ" "LU" "FR" "AT"]
         :phone-prefix "49"
         :population 82369000
         :post-code-format "#####"
         :post-code-regexp "^(\\d{5})$"
         :top-level-domain ".de")))

(deftest test-parse-countries
  (let [countries (parse-countries)]
    (is (= (count countries) 248))))

