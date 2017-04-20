(ns {{name}}.events-test
  (:require-macros [cljs.test :refer [deftest testing is]])
  (:require [potok.core :as ptk]
            [{{name}}.store :as store]
            [{{name}}.events :as events]))

(defonce initial-state (:state store/initial-state))

(deftest test-button-events
  (testing "Button Clicked"
    (let [event (events/ButtonClicked.)
          new-state (ptk/update event initial-state)]
      (is (:button/clicked new-state)))))
