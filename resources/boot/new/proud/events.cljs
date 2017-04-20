(ns {{name}}.events
    (:require [potok.core :as ptk]))

(defrecord ButtonClicked []
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :button/clicked true)))
