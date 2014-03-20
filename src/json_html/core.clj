(ns json-html.core
  (:require [cheshire.core :refer :all]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [html5]]
            [hiccup.util :refer [escape-html]])
  (:import [clojure.lang APersistentMap APersistentVector ISeq]))

(defprotocol Render
  (render [this] "Renders the element as HTML"))

(extend-protocol Render
  nil
  (render [_] [:span.jh-empty nil])

  String
  (render [this] [:span.jh-type-string (escape-html this)])

  Boolean
  (render [this] [:span.jh-type-bool this])

  Integer
  (render [this] [:span.jh-type-int.jh-type-number this])

  Double
  (render [this] [:span.jh-type-double.jh-type-number this])

  Float
  (render [this] [:span.jh-type-float.jh-type-number this])

  APersistentMap
  (render [this]
          [:table.jh-type-object
           (for [[k v] this]
            [:tr
             [:th.jh-key.jh-object-key k]
             [:td.h-value.jh-object-value (render v)]])])

  APersistentVector
  (render [this]
          [:table.jh-type-object
           (for [[i v] (map-indexed vector this)]
            [:tr [:th.jh-key.jh-array-key i]
             [:td.jh-value.jh-array-value (render v)]])]))


(defn edn->html [edn]
  (html5
   [:link {:rel "stylesheet" :href "resources/json.human.css"}]
   [:table.jh-root
    [:tr [:td (render edn)]]]))

(defn json->html [json]
  (->> json parse-string edn->html))
