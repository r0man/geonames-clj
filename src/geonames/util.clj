(ns geonames.util
  (:import java.net.URLEncoder)
  (:use [clojure.contrib.string :only (join)]))

(defmulti url-encode
  "Returns a url encoded string of the given object."
  (fn [obj & [encoding]] (class obj)))

(defmethod url-encode String [string & [encoding]]
  (. URLEncoder encode string (or encoding "UTF-8")))

(defmethod url-encode clojure.lang.Keyword [keyword & [encoding]]
  (url-encode (name keyword) encoding))

(defmethod url-encode clojure.lang.IPersistentMap [args & [encoding]]
  (join "&" (map (fn [[k v]] (str (url-encode k encoding) "=" (url-encode v encoding))) args)))

(defmethod url-encode :default [arg & [encoding]]
  (url-encode (str arg) encoding))
