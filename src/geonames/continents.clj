(ns geonames.continents
  (:use [clojure.contrib.seq-utils :only (find-first)]))

(defstruct continent :name :iso-3166-alpha-2 :geonames-id)

(def *continents*
     (map #(apply struct continent %)
          [["Africa" "AF" 6255146]
           ["Antarctica" "AN" 6255152]
           ["Asia" "AS" 6255147] 
           ["Europe" "EU" 6255148] 
           ["North America" "NA" 6255149] 
           ["Oceania" "OC" 6255151] 
           ["South America" "SA" 6255150]]))

(defn find-by-iso-3166-alpha-2 [code]
  (find-first #(= (:iso-3166-alpha-2 %) code) *continents*))

