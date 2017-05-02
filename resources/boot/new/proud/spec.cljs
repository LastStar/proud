(ns {{name}}.spec
    (:require [cljs.spec :as s]))

(s/def :button/clicked boolean?)
(s/def :ui/page keyword?)

(s/def ::state (s/keys :req [:button/clicked :ui/page]))
