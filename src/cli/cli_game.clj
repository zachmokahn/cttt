(ns cli.cli-game
  (:require [ttt.ai            :refer :all]
            [ttt.board         :refer :all]
            [cli.cli-players   :refer :all]
            [cli.cli-interface :refer :all]
            [ttt.constants     :refer :all]
            [cli.cli-messages  :refer :all]
            [ttt.rules         :refer :all]))

(defn get-winner [board]
  (if (win? board :player)
      (cli-human-win-message)
      (cli-computer-win-message)))

(defn get-results [board]
  (if (draw? board)
      (cli-draw-message)
      (get-winner board)))

(defn game [board turn]
  (cli-display-board board)
  (if (game-over? board)
      (get-results board)
      (let [index ((:move (turn cli-players)) board)]
          (if (valid-move? board index)
           (recur (move board index turn) (change-turn turn))
            (recur board turn)))))
