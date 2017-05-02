(ns {{name}}.routes
    (:require [bide.core :as router]))

(def config
  (router/router [["/about" :page/about]
                  ["/pricing" :page/pricing]]))
