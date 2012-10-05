(ns geonames.countries
  (:require [clojure.java.io :refer [reader]]
            [clojure.string :refer [split trim]]))

(def ^:dynamic *url*
  "http://download.geonames.org/export/dump/countryInfo.txt")

(def ^:dynamic *header*
  [:iso-3166-1-alpha-2 :iso-3166-1-alpha-3 :iso-3166-1-numeric :fips-code
   :name :capital :area :population :continent-code :top-level-domain
   :currency-code :currency-name :phone-prefix :post-code-format
   :post-code-regexp :languages :id :neighbours])

(defn- comment? [line]
  (not (nil? (re-find #"^\s*#.*$" line))))

(defn- parse-integer [string]
  (try (Integer/parseInt string)
       (catch NumberFormatException exception nil)))

(defn- parse-list [string]
  (if string (map trim (split string #","))))

(defn- parse-set [string]
  (if-let [list (parse-list string)]
    (set list)))

(defn- parse-line [line]
  (if-not (comment? line)
    (-> (zipmap *header* (split line #"\t"))
        (update-in [:area] parse-integer)
        (update-in [:id] parse-integer)
        (update-in [:iso-3166-1-numeric] parse-integer)
        (update-in [:languages] parse-set)
        (update-in [:neighbours] parse-set)
        (update-in [:population] parse-integer))))

(defn countries
  "Returns the Geonames countries."
  [& [source]]
  (->> (reader (or source *url*))
       (line-seq)
       (filter (complement comment?))
       (map parse-line)))
