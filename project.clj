(defproject ttt "1.0.0"
  :description "Command Line implementation of Tic Tac Toe in Clojure"
  :url "http://github.com/zachmokahn/cttt"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[speclj "2.9.1"]]}}
  :plugins [[speclj "2.9.1"]]
  :test-paths ["spec"]
  :main cli.core)
