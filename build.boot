(def project 'proud/boot-template)
(def version "0.2.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          ;; uncomment this if you write tests for your template:
          ;; :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "1.9.0-alpha15"]
                            [boot/new "0.5.2"]
                            [adzerk/bootlaces "0.1.13" :scope "test"]
                            [adzerk/boot-test "1.2.0" :scope "test"]])

(task-options!
 pom {:project     project
      :version     version
      :description "Highly opinionated boot template with rum, rum-mdl, potok, specs, tests and so on."
      :url         "https://laststar.github.io/proud"
      :scm         {:url "https://github.com/LastStar/proud"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

(require '[adzerk.boot-test :refer [test]]
         '[adzerk.bootlaces :refer :all]
         '[boot.new :refer [new]])

(bootlaces! version)
