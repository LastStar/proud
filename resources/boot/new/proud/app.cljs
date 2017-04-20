(ns {{name}}.app
    (:require [rum.core :as rum]
              [{{name}}.views :as views]
              [{{name}}.store :as store]))

(defn init []
  (rum/mount (views/main store/main)
             (. js/document (getElementById "container"))))
