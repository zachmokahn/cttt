(ns cli.cli-game
  (:require [cli.cli-players   :refer :all]
            [cli.cli-interface :refer :all]
            [cli.cli-messages  :refer :all]
            [ttt.board           :as board]
            [ttt.constants   :as constants]
            [ttt.rules           :as rules]))

(defn get-winner [board]
  (if (rules/win? board :player)
      (cli-human-win-message)
      (cli-computer-win-message)))

(defn get-results [board]
  (if (rules/draw? board)
      (cli-draw-message)
      (get-winner board)))

(defn game [board turn]
  (cli-display-board board constants/marker)
  (if (rules/game-over? board)
      (get-results board)
      (let [index ((:move (turn cli-players)) (board/empty-spaces board))]
          (if (rules/valid-move? board index)
           (recur (board/move board index turn) (rules/change-turn turn))
            (recur board turn)))))
