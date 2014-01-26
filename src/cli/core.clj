(ns cli.core
  (:require [cli.cli-game :refer :all]
            [ttt.board :as board]))

(defn -main []
  (game board/new-board :player))
