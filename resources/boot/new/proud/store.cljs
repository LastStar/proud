(ns {{name}}.store
    (:require [potok.core :as ptk]))

(defonce initial-state {:state {:button/clicked false :ui/page :page/about}})

(defonce main (ptk/store initial-state))

