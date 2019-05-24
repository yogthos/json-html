(ns ^:figwheel-no-load json-html.dev
  (:require
    [json-html.test-page :as test-page]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(test-page/init!)
