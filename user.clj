(require '[spyscope.core]
         '[clojure.test :as t]
         '[lucid.core.inject :as inject]
         '[lucid.core.debug :as debug]
         'lucid.core.namespace
         '[lucid.git :as git]
         'lucid.mind)

(ns user) ;; needed in order vinyasa.inject doesn't fail

(inject/in clojure.core - [clojure.repl apropos dir doc find-doc source pst root-cause]
           clojure.core - [clojure.pprint pprint print-table pp]
           clojure.core - [clojure.java.javadoc javadoc]
           clojure.core - [spyscope.repl trace-query trace-next trace-clear]
           clojure.core - [lucid.core.debug dbg-> dbg->>]
           clojure.core - [lucid.core.namespace clear-aliases clear-mappings]
           clojure.core - [lucid.git git]
           clojure.core - [lucid.mind .% .%> .* .?])

(inject/in clojure.core - [criterium.core bench quick-bench with-progress-reporting])

(try ;; try to inject logging functions, may not be loaded
  (require 'clojure.tools.logging)
  (inject/in clojure.core - [clojure.tools.logging info warn error])
  (catch Throwable e
    (.printStackTrace e)))
