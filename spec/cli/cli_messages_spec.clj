(ns cli.cli-messages-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]
            [cli.cli-messages :refer :all]))

(let [x (:player1 marker)
      o (:player2 marker)
      b (:blank marker)]

(describe "CLI MESSAGES SPEC"
  (describe "render"
    (it "return value if occupied"
      (should= (format " %s " x)
               (render [x x] 1)))
    (it "returns index if empty"
      (should= " 1 "
               (render [b b] 1))))
  (describe "display-board"
    (it "shows the board properly"
        (should=(str
                " 0 | 1 | 2 \n"
                "-----------\n"
                " 3 | 4 | 5 \n"
                "-----------\n"
                " 6 | 7 | 8 ")
                (display-board [b b b b b b b b b])
                  )))
  (describe "displays correct messages"
    (it "player-wins"
      (should= "banana has won the game!"
               (player-wins "banana")))
    (it "computer-wins"
      (should= "All hail the machine!"
               (computer-wins)))
    (it "move-prompt-message"
      (should= "Choose your fate"
               (move-prompt-message)))
    (it "game-mode-message"
      (should= (str
               "Please choose a game mode\n"
               "1. Player vs. Computer\n"
               "2. Player vs. Player\n")
               (game-mode-message)))
    (it "game-difficulty-message"
      (should= (str
               "Please choose a difficulty\n"
               "1. Difficult\n"
               "2. Easy\n")
               (game-difficulty-message))))))
