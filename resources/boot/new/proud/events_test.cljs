(ns {{name}}.events-test
  (:require-macros [cljs.test :refer [deftest testing is]])
  (:require [potok.core :as ptk]
            [beicon.core :as rx]
            [cljs.spec :as s]
            [{{name}}.store :as store]
            [{{name}}.events :as events]
            [{{name}}.spec]))

(defonce initial-state (:state store/initial-state))

(deftest test-click-state-button
  (testing "Set Button State"
    (let [event (events/->SetButtonState true)
          new-state (ptk/update event initial-state)]
      (is (:button/clicked new-state))
      (is (s/valid? :{{name}}.spec/state new-state))))
  (testing "Button Clicked"
    (let [event (events/->ButtonClicked)]
      (rx/on-value (ptk/watch event initial-state rx/empty)
                   #(is (= (events/->SetButtonState true) %)))))
  (testing "Button Clicked"
    (let [event (events/->ButtonUnclicked)
          emitted-events (atom #{})]
      (rx/on-value (ptk/watch event initial-state rx/empty)
                   #(swap! emitted-events conj %))
      (is (@emitted-events (events/->SetButtonState false)))
      (is (@emitted-events (events/->ShowAlert "Hey, hey, hey easy on that clicking!"))))))

(deftest test-routes
  (testing "Route Matched"
    (let [event (events/->RouteMatched :page/pricing nil nil)
          new-state (ptk/update event initial-state)]
      (is (= (:ui/page new-state) :page/pricing)))))

