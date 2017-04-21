(ns {{name}}.events-test
  (:require-macros [cljs.test :refer [deftest testing is]])
  (:require [potok.core :as ptk]
            [cljs.spec :as s]
            [{{name}}.store :as store]
            [{{name}}.events :as events]
            [{{name}}.spec]))

(defonce initial-state (:state store/initial-state))

(deftest test-button
  (testing "Button Clicked"
    (let [event (events/ButtonClicked.)
          new-state (ptk/update event initial-state)]
      (is (:button/clicked new-state))
      (is (s/valid? {{name}}.spec/state new-state)))))
