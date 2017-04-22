(defproject json-html "0.4.2"
  :description "Provide JSON and get a DOM node with a human representation of that JSON"
  :url "https://github.com/yogthos/json-html"
  :license {:name "MIT License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.521" :scope "provided"]
                 [hiccup "1.0.5"]
                 [hiccups "0.3.0"]
                 [cheshire "5.7.1"]]

  :clojurescript? true
  :jar-exclusions [#"\.swp|\.swo|\.DS_Store"]

  :plugins      [[lein-cljsbuild "1.1.5"]
                 [lein-doo "0.1.7"]]

  :cljsbuild
  {:builds
   {:main
    {:jar          true
     :source-paths ["src"]}
    :test
    {:source-paths ["src" "test"]
     :compiler     {:output-to     "target/test/tests.js"
                    :target        :nodejs
                    :optimizations :none
                    :source-map    true
                    :pretty-print  true
                    :main          json-html.runner}}}}

  :profiles
  {:dev
   {:jvm-opts     ["-XX:-TieredCompilation"]
    :source-paths ["src" "src-cljs" "test"]
    ;:hooks        [leiningen.cljsbuild]
    :dependencies [[commons-lang "2.6" :scope "test"]
                   [org.mozilla/rhino "1.7.7"]]}}


  :aliases {"test-cljs" ["doo" "node" "test" "once"]
            "test"      ["do" "test," "test-cljs"]
            "cleantest" ["do" "clean," "test"]
            "install"   ["do" "clean," "install"]}
  :doo {:build "test"})
