(ns json-html.test-page
  (:require
   [json-html.core :as core]
   [reagent.core :as r]
   [json-html.core :refer [edn->hiccup]]))

(def data
 {:description  "Convert\nJSON to human readable\r HTML"
  :tags         ["DOM" "HTML" "JSON" "Pretty Print"]
  :repository   {:type "git"
                 :url  "git://github.com/marianoguerra/json.human.js.git"}
  :license      "MIT"
  :config       {:emptyObject    {}
                 :what?          "this object is just to show some extra stuff"
                 :float          12.3
                 :emptyString    ""
                 :integer        42
                 :customization? ["customize the css prefix" "change the css file"]
                 :htmlEntities   "   <- trailing <em>   & </em> and some html   "
                 :how?           ["add json.human.js" "add json.human.css" "???" "profit!"]
                 :bool           true
                 :emptyArray     []}
  :name         "json.human"
  :bugs         {:url "https://github.com/yogthos/json-html/issues"}
  :author       "Mariano Guerra <mariano@marianoguerra.org>"
  :contributors []
  :version      "0.1.0"
  :main         "json.human.js"
  :dependencies {:crel "1.0.0"}})
 
 (defn home-page []
   [:p "Hello World!"]
   [edn->hiccup data])

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
