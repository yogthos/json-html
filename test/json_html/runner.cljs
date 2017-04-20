(ns json-html.runner
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [json-html.core-test]))

(doo-tests 'json-html.core-test)
