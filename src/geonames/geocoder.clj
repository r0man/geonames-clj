(ns geonames.geocoder
  (:require [clj-http.client :as client])
  (:use [clojure.data.json :only (read-str)]
        [clojure.string :as str]))

(defn city
  [address]
  (:place-name address))

(defn country
  [address]
  {:iso-3166-1-alpha-2 (str/lower-case (:country-code address))})

(defn location [address]
  (select-keys address [:lat :lng]))

(defn street-name [address]
  (:address-line (:address address)))

(defn postal-code [address]
  (:postal-code address))

(defn region [address]
  (:admin-name1 address))

(defn- request
  "Make a geocode request map."
  [geocoder & [opts]]
  {:request-method :get
   :query-params
   (assoc opts :username (:api-key geocoder))})

(defn fetch-json
  "Send the request, parse the hyphenated JSON body of the response."
  [request]
  (try (->> (merge
             {:as :auto
              :accept "application/json"
              :throw-exceptions true
              :coerce :always}
             request)
            (client/request)
            :body
            hyphenate-keys)
       (catch Exception e
         (throw (ex-info (str "Geocode request failed: " (.getMessage e))
                         (hyphenate-keys (ex-data e)))))))

(defn- fetch
  "Fetch and decode the Geonames geocode response."
  [request]
  (-> (fetch-json request)
      :postal-codes))

(defn geocode-address [geocoder address & [opts]]
  (-> (request geocoder opts)
      (assoc :url "http://api.geonames.org/postalCodeSearchJSON")
      (assoc-in [:query-params :placename] address)
      (fetch)))

(defn geocode-location [geocoder location & [opts]]
  (-> (request geocoder opts)
      (assoc :url (str "http://api.geonames.org/findNearbyPostalCodesJSON"))
      (assoc-in [:query-params :lat] (:lat location))
      (assoc-in [:query-params :lng] (:lng location))
      (fetch)))

(defn geocoder
  "Returns a new Geonames geocoder."
  [& [{:keys [api-key]}]]
  {:api-key api-key})
