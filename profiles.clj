{:user {:dependencies [[clj-stacktrace "0.2.8"]]
        :plugins      [[lein-difftest "2.0.0"]]}
 :repl {:pedantic? :warn
        :dependencies [[org.bouncycastle/bcprov-jdk15on "1.48"]
                       [org.opensaml/xmltooling "1.4.4"]
                       [zcaudate/lucid "1.4.4" :exclusions [org.bouncycastle/bcpg-jdk15on zcaudate/lucid.package]]
                       [org.clojure/tools.reader "1.3.2"]
                       [spyscope "0.1.7-SNAPSHOT" :exclusions [org.clojure/tools.reader]] ;; need to install locally
                       [com.google.guava/guava "22.0" :exclusions [com.google.code.findbugs/jsr305]]
                       [com.google.protobuf/protobuf-java "3.0.2"]
                       [org.clojure/tools.nrepl "0.2.13" :exclusions [org.clojure/clojure]]
                       [cljs-analyzer "0.1.0-SNAPSHOT"]
                       [cider/piggieback "0.3.10"]
                       [criterium "0.4.4"]]
        :plugins [[org.clojure/tools.nrepl "0.2.13"]
                  [cider/cider-nrepl "0.18.0"]
                  [refactor-nrepl "2.4.0" :exclusions [org.clojure/clojure]]]
        :repl-options {;;:nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                       :init             (load-file (str
                                                     (System/getProperty "user.home")
                                                     "/.lein/user.clj"))}
        :injections [(require 'spyscope.core)
                     (let [orig (ns-resolve (doto 'clojure.stacktrace require)
                                            'print-cause-trace)
                           new (ns-resolve (doto 'clj-stacktrace.repl require)
                                           'pst)]
                       (alter-var-root orig (constantly (deref new))))]}}
