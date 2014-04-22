(ns geonames.cities
  (:require [clojure.java.io :refer [reader input-stream]]
            [clojure.string :refer [split]]
            [geonames.util :refer :all])
  (:import (java.util.zip ZipInputStream)))

(def ^:dynamic *zip-url*
  "http://download.geonames.org/export/dump/cities15000.zip")

(def ^:dynamic *header*
  [:geoname-id 
   :name 
   :ascii-name 
   :alternate-names 
   :latitude 
   :longitude 
   :feature-class 
   :feature-code 
   :country-code 
   :cc2 
   :admin1-code 
   :admin2-code 
   :admin3-code 
   :admin4-code 
   :population 
   :elevation 
   :dem 
   :timezone 
   :modification-date])

(defn parse-line [line]
  (if-not (comment? line)
    (-> (zipmap *header* (split line #"\t"))
        (update-in [:geoname-id] parse-integer)
        (update-in [:latitude] parse-float)
        (update-in [:longitude] parse-float)
        (update-in [:population] parse-integer)
        (replace-blank-values))))

(defn cities-15000
  "Returns the Geonames cities with population over 15,000."
  [& [source]]
  (->> (reader (let [zip-stream (ZipInputStream. (input-stream (or source *zip-url*)))
                     zip-entry (.getNextEntry zip-stream)]
                 zip-stream))
       (line-seq)
       (filter (complement comment?))
       (map parse-line)))
