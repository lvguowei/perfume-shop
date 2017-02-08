(defproject perfumeshop "0.1.0-SNAPSHOT"
  :description "A perfume shop puzzle solver"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.logic "0.8.11"]]
  :main ^:skip-aot perfumeshop.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
