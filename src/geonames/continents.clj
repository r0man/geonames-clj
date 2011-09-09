(ns geonames.continents
  (:use [clojure.string :only (lower-case)]))

(defrecord Continent [name iso-3166-1-alpha-2 geonames-id])

(defn make-continent [name iso-3166-1-alpha-2 geonames-id]
  (Continent. name iso-3166-1-alpha-2 geonames-id))

(def ^:dynamic *continents*
  (map #(apply make-continent %)
       [["Africa" "af" 6255146]
        ["Antarctica" "an" 6255152]
        ["Asia" "as" 6255147]
        ["Europe" "eu" 6255148]
        ["North America" "na" 6255149]
        ["Oceania" "oc" 6255151]
        ["South America" "sa" 6255150]]))

(defn find-by-iso-3166-1-alpha-2 [code]
  (if-let [code (and code (lower-case code))]
    (first (filter #(= (:iso-3166-1-alpha-2 %) code) *continents*))))
