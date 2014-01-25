(ns cli.cli-messages
  (:require [ttt.constants :refer :all]))

(defn render [coll index]
  (str " " (if (= (get coll index) (:blank marker)) index (get coll index)) " "))

(defn display-board [board]
  (str
  (render board 0) "|" (render board 1) "|"  (render board 2)
  "\n-----------\n"
  (render board 3) "|" (render board 4) "|" (render board 5)
  "\n-----------\n"
  (render board 6) "|" (render board 7) "|" (render board 8)))

(defn draw-message []
  "Game is a draw")

(defn human-wins []
  "The human has won the game!")

(defn computer-wins []
  "All hail the machine!")

(defn move-prompt-message []
  "Choose your fate")
