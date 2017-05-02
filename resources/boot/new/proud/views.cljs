(ns {{name}}.views
    (:require [beicon.core :as rx]
              [rum.core :as rum]
              [rum.mdl :as mdl]
              [potok.core :as ptk]
              [{{name}}.events :as events]))

(rum/defc emitting-button [store label event]
  (mdl/button {:mdl [:ripple] :on-click #(ptk/emit! store event)} label))

(rum/defc title [current-page]
  (mdl/layout-title (str "{{name}} - " (name current-page))))

(rum/defc links [on-click]
  (mdl/nav
   (mdl/link {:href "#/about", :on-click on-click} "About")
   (mdl/link {:href "#/pricing", :on-click on-click} "Pricing")))

(rum/defc header < rum/reactive [store]
  (let [current-page (-> (rx/to-atom store) (rum/cursor :ui/page) rum/react)]
    (mdl/header
     (mdl/header-row
      (title current-page)
      (mdl/layout-spacer)
      (links nil)))))

(rum/defc drawer < rum/reactive [store]
  (let [current-page (-> (rx/to-atom store) (rum/cursor :ui/page) rum/react)]
    (mdl/drawer
     (title current-page)
     (links #(ptk/emit! store (events/->ToggleDrawer))))))

(rum/defc main-content < rum/reactive [store]
  (let [btn-clicked (-> (rx/to-atom store) (rum/cursor :button/clicked) rum/react)]
    (mdl/main-content
     (mdl/grid
      (mdl/cell
       {:mdl [:12]}
       "Find the code for this page in"
       [:code "`src/{{name}}/views.cljs`"])
      (if btn-clicked
        (mdl/cell
         {:mdl [:12]}
         [:p "Oh, you already figured out the clicking!"]
         (emitting-button store "Unclick?" (events/->ButtonUnclicked)))
        (mdl/cell
         {:mdl [:12]}
         (emitting-button store "Click me!" (events/->ButtonClicked))))))))

(rum/defc main [store]
  (mdl/layout
   (header store)
   (drawer store)
   (main-content store)))
