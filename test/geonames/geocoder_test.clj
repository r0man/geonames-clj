(ns geonames.geocoder-test
  (:use clojure.test
        geonames.geocoder))

(deftest test-find-nearby
  (let [places (find-nearby {:latitude 40.463667 :longitude -3.74922})]
    (is (= 1 (count places)))
    (let [place (first places)]
      (is (= -3.75200271606445 (:lng place)))
      (is (= "Spain" (:countryName place)))
      (is (= 6453264 (:geonameId place)))
      (is (= "spot, building, farm" (:fclName place)))
      (is (= "Centro de Tecnificación de Golf" (:name place)))
      (is (= "Centro de Tecnificación de Golf" (:toponymName place)))
      (is (= "29" (:adminCode1 place)))
      (is (= "RECG" (:fcode place)))
      (is (= 40.4674537049891 (:lat place)))
      (is (= 0 (:population place)))
      (is (= "Madrid" (:adminName1 place)))
      (is (= "ES" (:countryCode place)))
      (is (= "S" (:fcl place)))
      (is (= "0.48219" (:distance place)))
      (is (= "golf course" (:fcodeName place))))))

(deftest test-find-nearby-place-name
  (let [places (find-nearby-place-name {:latitude 40.463667 :longitude -3.74922})]
    (is (= 1 (count places)))
    (let [place (first places)]
      (is (= -3.7166667 (:lng place)))
      (is (= "Spain" (:countryName place)))
      (is (= 3106970 (:geonameId place)))
      (is (= "city, village,..." (:fclName place)))
      (is (= "Valdeconejos" (:name place)))
      (is (= "Valdeconejos" (:toponymName place)))
      (is (= "29" (:adminCode1 place)))
      (is (= "PPLX" (:fcode place)))
      (is (= 40.4666667 (:lat place)))
      (is (= 0 (:population place)))
      (is (= "Madrid" (:adminName1 place)))
      (is (= "ES" (:countryCode place)))
      (is (= "P" (:fcl place)))
      (is (= "2.78092" (:distance place)))
      (is (= "section of populated place" (:fcodeName place))))))

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

(deftest test-request
  (is (request "findNearbyPlaceNameJSON" {:lat 40.463667 :lng -3.74922})))
