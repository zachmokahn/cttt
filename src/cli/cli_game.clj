(ns cli.cli-game
  (:require [ttt.board         :refer :all]
            [ttt.rules         :refer :all]
            [cli.cli-options   :refer :all]
            [cli.cli-interface :refer :all]))

(defn get-winner [board players]
  (if (win? board :player1)
    (if (= (:player1 players) :computer)
      (cli-computer-win-message)
      (cli-win-message "Player 1"))
    (if (= (:player2 players) :computer)
      (cli-computer-win-message)
      (cli-win-message "Player 2"))))

(defn get-results [board players]
  (if (draw? board)
    (cli-draw-message)
    (get-winner board players)))

(defn get-move [board turn players difficulty]
  (if (= (turn players) :player)
      (cli-prompt-for-move board)
      ((difficulty cli-computer-difficulty) board turn)))

(defn game [board turn option]
  (let [game-mode  (:game-mode  option)
        players    (game-mode cli-game-play)]
  (cli-display-board board)
    (if (game-over? board)
      (get-results board players)
      (let [difficulty (:difficulty option)
            index      (get-move board turn players difficulty)
            next-board (move board index turn)
            opponent   (change-turn turn)]
      (if (valid-move? board index)
        (recur next-board opponent option)
        (recur board turn option))))))
