(ns geonames.test.geocoder
  (:use [clojure.java.io :only (reader)]
        clojure.contrib.mock
        clojure.test
        geonames.geocoder
        geonames.test))

(refer-private 'geonames.geocoder)

(def example-response
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
                     (times 1 (returns (:geonames example-response))))]
    (is (= (find-nearby {:latitude 40.463667 :longitude -3.74922})
           (:geonames example-response)))))

(deftest test-find-nearby-place-name
  (expect [geonames.geocoder/json-request
           (has-args ["http://ws.geonames.org/findNearbyPlaceNameJSON?lng=-3.74922&lat=40.463667"]
                     (times 1 (returns (:geonames example-response))))]
    (is (= (find-nearby-place-name {:latitude 40.463667 :longitude -3.74922})
           (:geonames example-response)))))

(deftest test-formatted-address
  (is (nil? (formatted-address nil)))
  (is (nil? (formatted-address {})))
  (testing "with country"
    (is (= (formatted-address {:countryName "Spain"}) "Spain")))
  (testing "with admin and country"
    (is (= (formatted-address {:countryName "Spain" :adminName1 "Basque Country"})
           "Basque Country, Spain")))
  (testing "with name, admin and country"
    (is (= (formatted-address {:countryName "Spain" :name "Mundaka" :adminName1 "Basque Country"})
           "Mundaka, Basque Country, Spain"))))
