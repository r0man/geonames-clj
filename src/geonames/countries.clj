(ns geonames.countries
  (:use [clojure.contrib.duck-streams :only (read-lines)]
        [clojure.contrib.string :only (lower-case split trim)]))

(def *url* "http://download.geonames.org/export/dump/countryInfo.txt")

(defrecord Country
  [area capital continent currency-code currency-name fips-code geonames-id
   iso-3166-1-alpha-2 iso-3166-alpha-3 iso-3166-numeric languages name neighbours 
   phone-prefix population post-code-format post-code-regexp top-level-domain])

(defn- comment? [line]
  (not (nil? (re-find #"^\s*#.*$" line))))

(defn- parse-integer [string]
  (try (Integer/parseInt string)
       (catch NumberFormatException exception nil)))

(defn- parse-list [string]
  (if string (map trim (split #"," string))))

(defn parse-country [line]  
  (if-not (comment? line)
    (let [[iso-3166-1-alpha-2 iso-3166-alpha-3 iso-3166-numeric fips-code
           name capital area population continent-code top-level-domain
           currency-code currency-name phone-prefix post-code-format
           post-code-regexp languages geonames-id neighbours]
          (split #"\t" line)]    
      (and name iso-3166-1-alpha-2 iso-3166-alpha-3 iso-3166-numeric continent-code
           (Country.
            (parse-integer area)
            (trim capital)
            {:iso-3166-1-alpha-2 (lower-case (trim continent-code))} 
            (trim currency-code)
            (trim currency-name)
            (trim fips-code)
            (parse-integer geonames-id)
            (lower-case (trim iso-3166-1-alpha-2))
            (lower-case (trim iso-3166-alpha-3))
            (parse-integer iso-3166-numeric)
            (map lower-case (parse-list languages))
            (trim name)
            (map lower-case (parse-list neighbours))
            (trim phone-prefix)
            (parse-integer population)
            (trim post-code-format)
            (trim post-code-regexp)
            (trim top-level-domain))))))

(defn parse-countries
  ([] (parse-countries *url*))
  ([source] (map parse-country (filter (complement comment?) (read-lines source)))))
