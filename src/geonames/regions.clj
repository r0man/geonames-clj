(ns geonames.regions
  (:use [clojure.contrib.duck-streams :only (read-lines)]
        [clojure.contrib.str-utils2 :only (split trim)]))

(def *url* "http://download.geonames.org/export/dump/admin1Codes.txt")

(defstruct region :name :country-id :region-id :geonames-id)

(defn parse-region [line]
  (if-let [match (re-find #"(..)\.([^\t]+)\t(.+)" line)]
    (let [[_ country-id region-id name] match]
      (struct region name country-id region-id (str country-id "." region-id)))))

(defn parse-regions
  ([] (parse-regions *url*))
  ([source] (map parse-region (read-lines source))))

