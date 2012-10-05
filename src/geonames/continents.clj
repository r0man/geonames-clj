(ns geonames.continents
  (:use [clojure.string :only [lower-case]]))

(def ^:dynamic *continents*
  #{{:id 6255146 :code "AF" :name "Africa"}
    {:id 6255152 :code "AN" :name "Antarctica"}
    {:id 6255147 :code "AS" :name "Asia"}
    {:id 6255148 :code "EU" :name "Europe"}
    {:id 6255149 :code "NA" :name "North America"}
    {:id 6255151 :code "OC" :name "Oceania"}
    {:id 6255150 :code "SA" :name "South America"}})

(defn continent-by-code
  "Returns the Geonames continent by `code`."
  [code] (first (filter #(= (lower-case code) (lower-case (:code %1))) *continents*)))

(defn continent-by-name
  "Returns the Geonames continent by `name`."
  [name] (first (filter #(= (lower-case name) (lower-case (:name %1))) *continents*)))

(defn continent-by-id
  "Returns the Geonames continent by `id`."
  [id] (first (filter #(= id (:id %1)) *continents*)))
