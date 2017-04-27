(ns {{name}}.views
    (:require [beicon.core :as rx]
              [rum.core :as rum]
              [rum.mdl :as mdl]
              [potok.core :as ptk]
              [{{name}}.events :as events]))

(rum/defc emitting-button [store label event]
  (mdl/button {:mdl [:ripple] :on-click #(ptk/emit! store event)} label))

(rum/defc main < rum/reactive [store]
  (let [state (rx/to-atom store)
        button-clicked (rum/react (rum/cursor state :button/clicked))]
    (mdl/layout
     (mdl/header
      (mdl/header-row
       (mdl/layout-title "Fresh {{name}} project ")))
     (mdl/main-content
      (mdl/grid
       (mdl/cell
        {:mdl [:12]}
        "Find the code for this page in"
        [:code "`src/{{name}}/views.cljs`"])
       (if button-clicked
         (mdl/cell
          {:mdl [:12]}
          [:h3 "Oh, you already figured out the clicking!"]
          (emitting-button store "Unclick?" (events/->ButtonUnclicked)))
         (mdl/cell
          {:mdl [:12]}
          (emitting-button store "Click me!" (events/->ButtonClicked)))))))))
