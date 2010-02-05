(ns geonames.country-info
  (:use [clojure.contrib.duck-streams :only (read-lines)]
        [clojure.contrib.str-utils2 :only (split trim)]))

(def *url* "http://download.geonames.org/export/dump/countryInfo.txt")

(defstruct country :continent :name :iso-3166 :capital :area :population :top-level-domain :currency :phone)

(defn- parse-integer [string]
  (try (Integer/parseInt string)
       (catch NumberFormatException exception nil)))

(defn- valid-country? [country]
  (and country (:name country) (:iso-3166 country)))

(defn comment? [line]
  (not (nil? (re-find #"^\s*#.*$" line))))

(defn parse-country [line]  
  (let [[iso-3166-alpha-2 iso-3166-alpha-3 iso-3166-numeric fips name
         capital area population continent top-level-domain
         currency-code currency-name phone _ _ _ _ _ _ _ _ _ _ _]
        (split line #"\t")]    
    (and name iso-3166-alpha-2 iso-3166-alpha-3 iso-3166-numeric continent
         (struct-map country
           :iso-3166 {:alpha-2 (trim iso-3166-alpha-2) :alpha-3 (trim iso-3166-alpha-3) :numeric (parse-integer iso-3166-numeric) }
           :name name
           :capital (trim capital)
           :area (parse-integer area)
           :population (parse-integer population)
           :continent { :code continent }
           :top-level-domain  (trim top-level-domain)
           :currency { :code (trim currency-code) :name (trim currency-name) }
           :phone phone))))

(defn parse-countries
  ([] (parse-countries *url*))
  ([source] (filter valid-country? (map parse-country (read-lines source)))))

