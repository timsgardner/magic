(ns run
  (:refer-clojure :exclude [compile])
  (:require 
    [magic.core :as magic]
    [magic.analyzer :as ana]
    [mage.core :as il]
    [magic.tests-generative :as g]))

(defn compile
  "Compile file to file.dll using magic"
  [file]
  (il/emit!
    (il/assembly+module
      (str file)
      (-> (str file)
          (slurp :encoding "utf8")
          read-string
          ana/analyze
          magic/symbolize)))
  (println (str "Built " file ".dll")))

(defn tests
  "Run analysis tests"
  []
  (println "test-ctors...")
  (g/test-ctors)
  (println "test-fields...")
  (g/test-fields)
  (println "test-properties...")
  (g/test-properties)
  (println "test-methods...")
  (g/test-methods))