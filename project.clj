(defproject json-html "0.4.6"
  :description "Provide JSON and get a DOM node with a human representation of that JSON"
  :url "https://github.com/yogthos/json-html"
  :license {:name "MIT License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies
  [[org.clojure/clojure "1.10.1" :scope "provided"]
   [org.clojure/clojurescript "1.10.520" :scope "provided"]
   [org.clojure/data.json "0.2.6"]]

  :plugins
  [[lein-cljsbuild "1.1.7"]
   [lein-figwheel "0.5.18"]
   [cider/cider-nrepl "0.21.1"]
   [lein-doo "0.1.10"]]

  :clojurescript? true
  :jar-exclusions [#"\.swp|\.swo|\.DS_Store"]
  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :profiles
  {:dev
   {:dependencies
    [[ring-server "0.5.0"]
     [ring-webjars "0.2.0"]
     [ring "1.7.1"]
     [ring/ring-defaults "0.3.2"]
     [compojure "1.6.1"]
     [hiccup "1.0.5"]
     [nrepl "0.6.0"]
     [binaryage/devtools "0.9.10"]
     [cider/piggieback "0.4.1"]
     [figwheel-sidecar "0.5.18"]
     [reagent "0.8.1"]]

    :source-paths ["src" "env/dev/clj" "env/dev/cljs"]
    :resource-paths ["resources" "env/dev/resources" "target/cljsbuild"]

    :figwheel
    {:server-port      3450
     :nrepl-port       7000
     :nrepl-middleware [cider.piggieback/wrap-cljs-repl
                        cider.nrepl/cider-middleware]
     :css-dirs         ["resources/public/css" "env/dev/resources/public/css"]
     :ring-handler     json-html.server/app}
    :cljsbuild
    {:builds
     {:app
      {:source-paths ["src" "env/dev/cljs"]
       :figwheel     {:on-jsload "json-html.test-page/mount-root"}
       :compiler     {:main          json-html.dev
                      :asset-path    "/js/out"
                      :output-to     "target/cljsbuild/public/js/app.js"
                      :output-dir    "target/cljsbuild/public/js/out"
                      :source-map-timestamp true
                      :source-map    true
                      :optimizations :none
                      :pretty-print  true}}}}}
   :test
   {:cljsbuild
    {:builds
     {:test
      {:source-paths ["src" "test"]
       :compiler     {:main          json-html.runner
                      :output-to     "target/test/core.js"
                      :target        :nodejs
                      :optimizations :none
                      :source-map    true
                      :pretty-print  true}}}}
    :doo {:build "test"}}}
    :aliases
    {"test-cljs"  ["do" ["clean"] ["with-profile" "test" "doo" "node" "once"]]
     "test-clj"   ["do" "clean," "test"]
     "test"       ["do" "test," "test-cljs"]
     "cleantest"  ["do" "clean," "test"]
     "install"    ["do" "clean," "install"]
     "test-watch" ["do" ["clean"]["with-profile" "test" "doo" "node"]]})
