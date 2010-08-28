(ns geonames.test.geocoder
  (:use [clojure.contrib.def :only (defvar)]
        [clojure.contrib.duck-streams :only (reader)]
        clojure.contrib.mock
        clojure.test
        geonames.geocoder
        geonames.test))

(refer-private 'geonames.geocoder)

(defvar *response*
  {:geonames [{:lng -3.7166667, :continentCode "EU", :countryName "Spain", :geonameId 3106970, :elevation 0, :adminName4 "",
               :fclName "city, village,...", :adminName3 "Madrid", :timezone {:dstOffset 2, :gmtOffset 1, :timeZoneId "Europe/Madrid"},
               :adminName2 "Province of Madrid", :name "Valdeconejos", :toponymName "Valdeconejos", :adminCode2 "M", :adminCode3 "28079",
               :adminCode1 "29", :fcode "PPLX", :lat 40.4666667, :population 0, :adminName1 "Autonomous Region of Madrid", :countryCode "ES",
               :fcl "P", :distance "2.7739", :fcodeName "section of populated place"}]})

(deftest test-endpoint-url
  (is (= (endpoint-url "findNearbyJSON" {})
         "http://ws.geonames.org/findNearbyJSON"))
  (is (= (endpoint-url "findNearbyJSON" {:lat 40.463667 :lng -3.74922})
         "http://ws.geonames.org/findNearbyJSON?lat=40.463667&lng=-3.74922")))

(deftest test-json-request
  (let [url "http://ws.geonames.org/findNearbyPlaceNameJSON?lat=40.463667&lng=-3.74922"]
    (expect [reader (has-args [url] (returns "{\"geonames\": []}"))]
      (is (= (json-request url) [])))))

(deftest test-find-nearby
  (expect [geonames.geocoder/json-request
           (has-args ["http://ws.geonames.org/findNearbyJSON?lng=-3.74922&lat=40.463667"]
                     (times 1 (returns (:geonames *response*))))]
    (is (= (find-nearby {:latitude 40.463667 :longitude -3.74922})
           (:geonames *response*)))))

(deftest test-find-nearby-place-name
  (expect [geonames.geocoder/json-request
           (has-args ["http://ws.geonames.org/findNearbyPlaceNameJSON?lng=-3.74922&lat=40.463667"]
                     (times 1 (returns (:geonames *response*))))]
    (is (= (find-nearby-place-name {:latitude 40.463667 :longitude -3.74922})
           (:geonames *response*)))))
