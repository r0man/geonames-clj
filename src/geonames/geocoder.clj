(ns geonames.geocoder
  (:use [clojure.java.io :only (reader)]
        [clojure.data.json :only (read-json)]
        [clojure.string :only (blank? lower-case join)]
        [geonames.util :only (url-encode)]))

(def ^:dynamic *base-url* "http://ws.geonames.org")

(defn- endpoint-url [endpoint params]
  (if (blank? endpoint) (throw "No endpoint.")
      (str *base-url* "/" endpoint
           (if-not (empty? params) (str "?" (url-encode params))))))

(defn- json-request [url]
  (let [response (read-json (reader url))]
    (if-let [result (:geonames response)]
      result (throw (Exception. (str response))))))

(defn find-nearby [location & options]
  (let [options (apply hash-map options)]
    (json-request (endpoint-url "findNearbyJSON" (merge options {:lat (:latitude location) :lng (:longitude location)})))))

(defn find-nearby-place-name [location & options]
  (let [options (apply hash-map options)]
    (json-request (endpoint-url "findNearbyPlaceNameJSON" (merge options {:lat (:latitude location) :lng (:longitude location)})))))

(defn formatted-address
  "Returns the formatted address of the result."
  [result]
  (let [address (join ", " (remove blank? [(:name result) (:adminName1 result) (:countryName result)]))]
    (if-not (blank? address)
      address)))
