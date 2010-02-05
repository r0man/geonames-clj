(ns geonames.country-info
  (:use [clojure.contrib.duck-streams :only (read-lines)]
        [clojure.contrib.str-utils2 :only (split trim)]))

(def *url* "http://download.geonames.org/export/dump/countryInfo.txt")

(defstruct country
  :area :capital :continent-code :currency-code :currency-name :fips-code :geoname-id
  :iso-3166-alpha-2 :iso-3166-alpha-3 :iso-3166-numeric :languages :name :neighbours 
  :phone-prefix :population :post-code-format :post-code-regexp :top-level-domain)

(defn- comment? [line]
  (not (nil? (re-find #"^\s*#.*$" line))))

(defn- parse-integer [string]
  (try (Integer/parseInt string)
       (catch NumberFormatException exception nil)))

(defn- valid-country? [country]
  (and country (:name country) (:iso-3166-alpha-2 country)))

(defn parse-country [line]  
  (let [[iso-3166-alpha-2 iso-3166-alpha-3 iso-3166-numeric fips-code
         name capital area population continent-code top-level-domain
         currency-code currency-name phone-prefix post-code-format
         post-code-regexp languages geoname-id neighbours  _ _ _ _ _ _ _]
        (split line #"\t")]    
    (and name iso-3166-alpha-2 iso-3166-alpha-3 iso-3166-numeric continent-code
         (struct-map country
           :area (parse-integer area)
           :capital (trim capital)
           :continent-code (trim continent-code) 
           :currency-code (trim currency-code)
           :currency-name (trim currency-name)
           :fips-code (trim fips-code)
           :geoname-id (parse-integer geoname-id)
           :iso-3166-alpha-2 (trim iso-3166-alpha-2)
           :iso-3166-alpha-3 (trim iso-3166-alpha-3)
           :iso-3166-numeric (parse-integer iso-3166-numeric)
           :languages (split (trim languages) #",")
           :name (trim name)
           :neighbours (if neighbours (split (trim neighbours) #",")) 
           :phone-prefix (trim phone-prefix)
           :population (parse-integer population)
           :post-code-format (trim post-code-format)
           :post-code-regexp (trim post-code-regexp)
           :top-level-domain (trim top-level-domain)))))

(defn parse-countries
  ([] (parse-countries *url*))
  ([source] (filter valid-country? (map parse-country (read-lines source)))))
