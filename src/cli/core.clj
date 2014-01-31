(ns cli.core
  (:require [ttt.board :refer :all]
            [cli.cli-interface :refer :all]
            [cli.cli-game :refer :all]))

(defn -main []
  (game new-board :player1 (cli-prompt-for-options)))
