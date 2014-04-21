(ns geonames.cities
  (:require [clojure.java.io :refer [reader input-stream]]
            [clojure.string :refer [split]]
            [geonames.util :refer :all])
  (:import (java.util.zip ZipInputStream)))

(def ^:private ^:dynamic *zip-url*
  "http://download.geonames.org/export/dump/cities15000.zip")

(def ^:private ^:dynamic *header*
  [:geonameid :name :asciiname :alternatenames :latitude :longitude :feature_class :feature_code :country_code :cc2 :admin1_code :admin2_code :admin3_code :admin4_code :population :elevation :dem :timezone :modification_date])

(defn ^:private parse-line [line]
  (if-not (comment? line)
    (-> (zipmap *header* (split line #"\t"))
        (update-in [:geonameid] parse-integer)
        ;; (update-in [:latitude] parse-integer)
        ;; (update-in [:longitude] parse-integer)
        (update-in [:population] parse-integer)
        (replace-blank-values))))

(defn cities15000
  "Returns the Geonames cities with population over 15,000."
  [& [source]]
  (->> (reader (let [zip-stream (ZipInputStream. (input-stream (or source *zip-url*)))
                     zip-entry (.getNextEntry zip-stream)]
                 zip-stream))
       (line-seq)
       (filter (complement comment?))
       (map parse-line)))
