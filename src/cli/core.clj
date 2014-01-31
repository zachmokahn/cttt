(ns cli.core
  (:require [ttt.board :refer :all]
            [cli.cli-interface :refer :all]
            [cli.cli-game :refer :all]))

(defn -main []
  (cli-clear-screen)
  (game new-board :player1 (cli-prompt-for-options))
  (System/exit 0))
