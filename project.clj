(defproject json-html "0.3.9"
  :description "Provide JSON and get a DOM node with a human representation of that JSON"
  :url "https://github.com/yogthos/json-html"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-2322" :scope "provided"]
                 [hiccup "1.0.5"]
                 [hiccups "0.3.0"]
                 [cheshire "5.3.1"]]

  :clojurescript? true
  :jar-exclusions [#"\.swp|\.swo|\.DS_Store"]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild
  {:builds
     {:main
      {:jar true
       :source-paths ["src-cljs"]}}})
