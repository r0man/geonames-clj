(ns geonames.regions
  (:use [clojure.contrib.duck-streams :only (read-lines)]
        [clojure.contrib.string :only (trim)]))

(def *url* "http://download.geonames.org/export/dump/admin1CodesASCII.txt")

(defstruct region :name :country-id :region-id :geonames-id)

(defn parse-region [line]
  (if-let [[_ geonames-id country-id region-id name] (re-find #"((..)\.([^\t]+))\t(.+)" line)]
    (struct region (trim name) (trim country-id) (trim region-id) (trim geonames-id))))

(defn parse-regions
  ([] (parse-regions *url*))
  ([source] (map parse-region (read-lines source))))
