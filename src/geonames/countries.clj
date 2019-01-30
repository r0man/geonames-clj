(ns geonames.countries
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [geonames.util :as util]))

(def ^:dynamic *url*
  "http://download.geonames.org/export/dump/countryInfo.txt")

(def ^:dynamic *header*
  [:iso-3166-1-alpha-2 :iso-3166-1-alpha-3 :iso-3166-1-numeric :fips-code
   :name :capital :area :population :continent-code :top-level-domain
   :currency-code :currency-name :phone-prefix :post-code-format
   :post-code-regexp :languages :id :neighbours])

(defn- trim-str [s]
  (some-> s str/trim))

(defn parse-line [line]
  (if-not (util/comment? line)
    (-> (zipmap *header* (str/split line #"\t"))
        (update-in [:name] trim-str)
        (update-in [:area] util/parse-integer)
        (update-in [:id] util/parse-integer)
        (update-in [:iso-3166-1-numeric] util/parse-integer)
        (update-in [:languages] util/parse-set)
        (update-in [:neighbours] util/parse-set)
        (update-in [:population] util/parse-integer)
        (util/replace-blank-values))))

(defn countries
  "Returns the Geonames countries."
  [& [source]]
  (->> (io/reader (or source *url*))
       (line-seq)
       (filter (complement util/comment?))
       (map parse-line)))
