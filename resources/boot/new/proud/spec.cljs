(ns {{name}}.spec
    (:require [cljs.spec :as s]))

(s/def :button/clicked boolean?)

(s/def ::state (s/keys :req [:button/clicked]))
