(ns cli.core
  (:require [ttt.board :refer :all]
            [cli.cli-game :refer :all]))

(defn -main []
  (game new-board :player))
