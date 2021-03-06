(set-env!
 :source-paths    #{"src"}
 :resource-paths  #{"resources"}
 :dependencies    '[[org.clojure/clojurescript     "1.9.293"]
                    [org.clojure/tools.nrepl       "0.2.13"     :scope "test"]
                    [adzerk/boot-cljs              "1.7.228-2"  :scope "test"]
                    [adzerk/boot-cljs-repl         "0.3.3"      :scope "test"]
                    [adzerk/boot-reload            "0.4.13"     :scope "test"]
                    [pandeiro/boot-http            "0.7.6"      :scope "test"]
                    [com.cemerick/piggieback       "0.2.1"      :scope "test"]
                    [weasel                        "0.7.0"      :scope "test"]
                    [rum                           "0.10.7"]
                    [rum-mdl                       "0.2.0"]
                    [funcool/potok                 "2.1.0"]
                    [funcool/bide                  "1.4.0"]
                    [binaryage/devtools            "0.9.4"      :scope "test"]
                    [binaryage/dirac               "1.2.6"      :scope "test"]
                    [powerlaces/boot-cljs-devtools "0.2.0"      :scope "test"]
                    [crisptrutski/boot-cljs-test   "0.3.0"      :scope "test"]])

(require
 '[adzerk.boot-cljs              :refer [cljs]]
 '[adzerk.boot-cljs-repl         :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload            :refer [reload]]
 '[pandeiro.boot-http            :refer [serve]]
 '[powerlaces.boot-cljs-devtools :refer [cljs-devtools dirac]]
 '[crisptrutski.boot-cljs-test   :refer [test-cljs]])

(deftask build []
  (comp (speak)
        (cljs)))

(deftask run []
  (comp (serve)
        (watch)
        (cljs-repl)
        (cljs-devtools)
        (dirac)
        (reload)
        (build)))

(deftask production []
  (task-options! cljs {:optimizations :advanced})
  identity)

(deftask development []
  (task-options! cljs {:optimizations :none}
                 reload {:on-jsload '{{name}}.app/init})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp (development)
        (run)))

(deftask testing []
  (set-env! :source-paths #(conj % "test"))
  identity)

;;; This prevents a name collision WARNING between the test task and
;;; clojure.core/test, a function that nobody really uses or cares
;;; about.
(ns-unmap 'boot.user 'test)

(deftask test []
  (comp (testing)
        (test-cljs :js-env :phantom
                   :exit?  true)))

(deftask auto-test []
  (comp (testing)
        (watch)
        (test-cljs :js-env :phantom)))
