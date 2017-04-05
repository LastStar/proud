(ns boot.new.proud
  (:require [boot.new.templates :refer [renderer name-to-path ->files]]))

(def render (renderer "proud"))

(defn proud
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (println "Generating fresh 'boot new' proud project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
