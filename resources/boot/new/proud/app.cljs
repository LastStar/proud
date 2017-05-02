(ns {{name}}.app
    (:require [rum.core :as rum]
              [potok.core :as ptk]
              [bide.core :as router]
              [{{name}}.views :as views]
              [{{name}}.routes :as routes]
              [{{name}}.store :as store]
              [{{name}}.events :as events]))

(defn init []
  (router/start!
   routes/config
   {:default     :page/about
    :on-navigate (fn [name params query]
                   (ptk/emit! store/main
                              (events/->RouteMatched name params query)))})
  (rum/mount (views/main store/main)
             (. js/document (getElementById "container"))))
