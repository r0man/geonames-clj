(ns geonames.countries
  (:require [clojure.java.io :refer [reader]]
            [clojure.string :refer [split]]
            [geonames.util :refer :all]))

(def ^:dynamic *url*
  "http://download.geonames.org/export/dump/countryInfo.txt")

(def ^:dynamic *header*
  [:iso-3166-1-alpha-2 :iso-3166-1-alpha-3 :iso-3166-1-numeric :fips-code
   :name :capital :area :population :continent-code :top-level-domain
   :currency-code :currency-name :phone-prefix :post-code-format
   :post-code-regexp :languages :id :neighbours])

(defn parse-line [line]
  (if-not (comment? line)
    (-> (zipmap *header* (split line #"\t"))
        (update-in [:area] parse-integer)
        (update-in [:id] parse-integer)
        (update-in [:iso-3166-1-numeric] parse-integer)
        (update-in [:languages] parse-set)
        (update-in [:neighbours] parse-set)
        (update-in [:population] parse-integer)
        (replace-blank-values))))

(defn countries
  "Returns the Geonames countries."
  [& [source]]
  (->> (reader (or source *url*))
       (line-seq)
       (filter (complement comment?))
       (map parse-line)))
