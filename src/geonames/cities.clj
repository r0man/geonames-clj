(ns geonames.cities
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [geonames.util :as util])
  (:import java.util.zip.ZipInputStream))

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
  (if-not (util/comment? line)
    (-> (zipmap *header* (str/split line #"\t"))
        (update-in [:geoname-id] util/parse-integer)
        (update-in [:latitude] util/parse-float)
        (update-in [:longitude] util/parse-float)
        (update-in [:population] util/parse-integer)
        (util/replace-blank-values))))

(defn cities-15000
  "Returns the Geonames cities with population over 15,000."
  [& [source]]
  (->> (io/reader (let [zip-stream (ZipInputStream. (io/input-stream (or source *zip-url*)))
                        zip-entry (.getNextEntry zip-stream)]
                    zip-stream))
       (line-seq)
       (filter (complement util/comment?))
       (map parse-line)))
