(ns {{name}}.views
    (:require [beicon.core :as rx]
              [rum.core :as rum]
              [rum.mdl :as mdl]
              [potok.core :as ptk]
              [{{name}}.events :as events]))

(rum/defc emitting-button [store label event]
  (mdl/button
   {:mdl [:ripple]
    :on-click #(ptk/emit! store event)}
   label))

(rum/defc main < rum/reactive [store]
  (let [state (rx/to-atom store)
        button-clicked (rum/react (rum/cursor state :button/clicked))]
    [:div
     [:h1 "Fresh {{name}} project "]
     [:h2
      "Find the code for this page in "
      [:code "`src/{{name}}/views.cljs`"]]
     (if button-clicked
       [:div
        [:h3 "Oh, you already figured out the clicking!"]
        (emitting-button store "Unclick?" (events/->ButtonUnclicked))]
       (emitting-button store "Click me!" (events/->ButtonClicked)))]))
