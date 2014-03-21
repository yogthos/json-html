(ns json-html.core
  (:require [cheshire.core :refer :all]
            [hiccup.core :refer [html]]
            [hiccup.util :refer [escape-html]])
  (:import [clojure.lang IPersistentMap IPersistentCollection Keyword]))

(defprotocol Render
  (render [this] "Renders the element a Hiccup structure"))

(extend-protocol Render
  nil
  (render [_] [:span.jh-empty nil])

  java.util.Date
  (render [this]
    [:span.jh-type-date
      (.format (new java.text.SimpleDateFormat "MMM dd, yyyy HH:mm:ss") this)])

  String
  (render [this] [:span.jh-type-string (escape-html this)])

  Boolean
  (render [this] [:span.jh-type-bool this])

  Integer
  (render [this] [:span.jh-type-number this])

  Double
  (render [this] [:span.jh-type-number this])

  Long
  (render [this] [:span.jh-type-number this])

  Float
  (render [this] [:span.jh-type-number this])

  Keyword
  (render [this] [:span.jh-type-string (name this)])

  IPersistentMap
  (render [this]
    [:table.jh-type-object
      (for [[k v] this]
        [:tr [:th.jh-key.jh-object-key k]
             [:td.jh-value.jh-object-value (render v)]])])

  IPersistentCollection
  (render [this]
    [:table.jh-type-object
      (for [[i v] (map-indexed vector this)]
        [:tr [:th.jh-key.jh-array-key i]
             [:td.jh-value.jh-array-value (render v)]])])

  Object
  (render [this]
    [:span.jh-type-string (.toString this)]))


(defn edn->html [edn]
  (html
   [:div.jh-root
    (render edn)]))

(defn json->html [json]
  (-> json (parse-string false) edn->html))

