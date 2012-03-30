(ns geonames.geocoder
  (:require [clj-http.client :as client])
  (:use [clojure.data.json :only (read-json)]
        [clojure.string :only (blank? lower-case join)]))

(def ^:dynamic *base-url* "http://ws.geonames.org")
(def ^:dynamic *key* "demo")

(defn request [endpoint & [query-params]]
  (->> (client/request
        {:url (str *base-url* "/" endpoint)
         :method :get
         :query-params (assoc query-params :key *key*)})
       :body read-json :geonames))

(defn find-nearby [location & {:as options}]
  (request "findNearbyJSON" (assoc options :lat (:latitude location) :lng (:longitude location))))

(defn find-nearby-place-name [location & {:as options}]
  (request "findNearbyPlaceNameJSON" (assoc options :lat (:latitude location) :lng (:longitude location))))

(defn formatted-address
  "Returns the formatted address of the result."
  [result]
  (let [address (join ", " (remove blank? [(:name result) (:adminName1 result) (:countryName result)]))]
    (if-not (blank? address)
      address)))

(defmacro with-key
  "Evaluate `body` with *key* bound to `key`."
  [key & body] `(binding [*key* ~key] ~@body))