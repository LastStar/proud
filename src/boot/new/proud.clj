(ns boot.new.proud
  (:require [boot.new.templates :refer [renderer name-to-path ->files]]))

(def render (renderer "proud"))

(defn proud
  "Generates new Proud project"
  [name]
  (let [data {:name         name
              :sanitized    (name-to-path name)}]
    (println "Generating fresh Proud project. Oh yes!")
    (apply (partial ->files data)
           [[".gitignore" (render "gitignore" data)]
            ["build.boot" (render "build.boot" data)]
            ["boot.properties" (render "boot.properties" data)]
            ["src/{{sanitized}}/app.cljs" (render "app.cljs" data)]
            ["src/{{sanitized}}/views.cljs" (render "views.cljs" data)]
            ["src/{{sanitized}}/events.cljs" (render "events.cljs" data)]
            ["src/{{sanitized}}/store.cljs" (render "store.cljs" data)]
            ["test/{{sanitized}}/events_test.cljs" (render "events_test.cljs" data)]
            ["resources/index.html" (render "index.html" data)]
            ["resources/js/app.cljs.edn" (render "app.cljs.edn" data)]
            ["resources/css/material.min.inc.css" (render "material.min.inc.css" data)]])))
