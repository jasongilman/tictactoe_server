(defproject tictactoe_server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [fogus/ring-edn "0.2.0-SNAPSHOT"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler tictactoe_server.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
