(ns geonames.test.countries
  (:use clojure.test geonames.countries))

(deftest test-parse-country
  (are [line] (is nil? (parse-country line))
       "# GeoNames.org Country Information"
       "# ================================"
       "# The column 'languages' lists the languages spoken in a country ordered by the number of speakers. The language code is a 'locale'"
       "#ISO\tISO3\tISO-Numeric\tfips\tCountry\tCapital\tArea(in sq km)\tPopulation\tContinent\ttld\tCurrencyCode\tCurrencyName\tPhone\tPostal Code Format\tPostal Code Regex\tLanguages\tgeonameid\tneighbours\tEquivalentFipsCode"
       "invalid\tinvalid\tinvalid\tinvalid\tinvalid\tinvalid"))

(deftest test-parse-country-with-faroe-islands
  (let [country (parse-country "FO\tFRO\t234\tFO\tFaroe Islands\tTórshavn\t1399\t48228\tEU\t.fo\tDKK\tKrone\t298\tFO-###\t^(?:FO)*(\\d{3})$\tfo,da-FO\t2622320")]
    (are [attribute expected] (= (get country attribute) expected)
         :area 1399
         :capital "Tórshavn"
         :continent {:iso-3166-1-alpha-2 "eu"}
         :currency-code "DKK"
         :currency-name "Krone"
         :fips-code "FO"
         :geonames-id 2622320
         :iso-3166-1-alpha-2 "fo"
         :iso-3166-1-alpha-3 "fro"
         :iso-3166-1-numeric 234
         :languages ["fo" "da-fo"]
         :name "Faroe Islands"
         :neighbours []
         :phone-prefix "298"
         :population 48228
         :post-code-format "FO-###"
         :post-code-regexp "^(?:FO)*(\\d{3})$"
         :top-level-domain ".fo")))

(deftest test-parse-country-with-germany
  (let [country (parse-country "DE\tDEU\t276\tGM\tGermany\tBerlin\t357021\t82369000\tEU\t.de\tEUR\tEuro\t49\t#####\t^(\\d{5})$\tde\t2921044\tCH,PL,NL,DK,BE,CZ,LU,FR,AT")]
    (are [attribute expected] (= (get country attribute) expected)
         :area 357021
         :capital "Berlin"
         :continent {:iso-3166-1-alpha-2 "eu"}
         :currency-code "EUR"
         :currency-name "Euro"
         :fips-code "GM"
         :geonames-id 2921044
         :iso-3166-1-alpha-2 "de"
         :iso-3166-1-alpha-3 "deu"
         :iso-3166-1-numeric 276
         :languages ["de"]
         :name "Germany"
         :neighbours ["ch" "pl" "nl" "dk" "be" "cz" "lu" "fr" "at"]
         :phone-prefix "49"
         :population 82369000
         :post-code-format "#####"
         :post-code-regexp "^(\\d{5})$"
         :top-level-domain ".de")))

(deftest test-parse-countries
  (let [countries (parse-countries)]
    (is (= (count countries) 248))))
