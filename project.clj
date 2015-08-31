(defproject json-html "0.3.5"
  :description "Provide JSON and get a DOM node with a human representation of that JSON"
  :url "https://github.com/yogthos/json-html"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2322"]
                 [hiccup "1.0.5"]
                 [hiccups "0.3.0"]
                 [cheshire "5.3.1"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :hooks [leiningen.cljsbuild]
  :clojurescript? true
  :cljsbuild
  {;:crossover-jar true
   :builds
   {:main
     {:jar true
      :source-paths ["src-cljs"]}}})
