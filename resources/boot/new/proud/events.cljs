(ns {{name}}.events
    (:require [potok.core :as ptk]
              [beicon.core :as rx]))

(defrecord ^:private SetButtonState [clicked]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :button/clicked clicked)))

(defrecord ^:private ShowAlert [message]
  ptk/EffectEvent
  (effect [_ state _]
    (js/alert message)))

(defrecord ButtonClicked []
  ptk/WatchEvent
  (watch [_ state _]
    (rx/just (->SetButtonState true))))

(defrecord ButtonUnclicked []
  ptk/WatchEvent
  (watch [_ state _]
    (rx/of (->SetButtonState false)
           (->ShowAlert "Hey, hey, hey easy on that clicking!"))))

(defrecord RouteMatched [name params query]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :ui/page name)))
