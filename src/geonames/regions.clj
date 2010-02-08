(ns geonames.regions
  (:use [clojure.contrib.duck-streams :only (read-lines)]))

(def *url* "http://download.geonames.org/export/dump/admin1Codes.txt")

(defstruct region :name :country-id :region-id :geonames-id)

(defn parse-region [line] 
  (if-let [[_ geonames-id country-id region-id name] (re-find #"((..)\.([^\t]+))\t(.+)" line)]    
    (struct region name country-id region-id geonames-id)))

(defn parse-regions
  ([] (parse-regions *url*))
  ([source] (map parse-region (read-lines source))))

