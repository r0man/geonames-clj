(ns geonames.regions
  (:use [clojure.java.io :only (reader)]
        [clojure.string :only (trim)]))

(def ^:dynamic *url* "http://download.geonames.org/export/dump/admin1CodesASCII.txt")

(defrecord Region [name country-id region-id geonames-id])

(defn parse-region [line]
  (if-let [[_ geonames-id country-id region-id name] (re-find #"((..)\.([^\t]+))\t(.+)" line)]
    (Region. (trim name) (trim country-id) (trim region-id) (trim geonames-id))))

(defn parse-regions
  [& [source]]
  (->> (reader (or source *url*))
       (line-seq)
       (map parse-region)))
