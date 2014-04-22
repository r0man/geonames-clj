(ns geonames.util
  (:require [clojure.string :refer [blank? split trim]]
            [clojure.walk :refer [postwalk]]))

(defn comment? [line]
  (not (nil? (re-find #"^\s*#.*$" line))))

(defn parse-integer [string]
  (try (Integer/parseInt string)
       (catch NumberFormatException exception nil)))

(defn parse-float [string]
  (try (Float/parseFloat string)
       (catch NumberFormatException exception nil)))

(defn parse-list [string]
  (if string (map trim (split string #","))))

(defn parse-set [string]
  (if-let [list (parse-list string)]
    (set list)))

(defn replace-blank-values
  "Recursively replace all blank values in m with nil."
  [m]
  (let [f (fn [[k v]] (if (and (string? v) (blank? v)) [k nil] [k v]))]
    (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))
