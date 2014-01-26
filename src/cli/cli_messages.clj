(ns cli.cli-messages)

(defn render [coll index marker]
  (str " " (if (= (get coll index) (:blank marker)) index (get coll index)) " "))

(defn display-board [board marker]
  (str
  (render board 0 marker) "|" (render board 1 marker) "|"  (render board 2 marker)
  "\n-----------\n"
  (render board 3 marker) "|" (render board 4 marker) "|" (render board 5 marker)
  "\n-----------\n"
  (render board 6 marker) "|" (render board 7 marker) "|" (render board 8 marker)))

(defn draw-message []
  "Game is a draw")

(defn human-wins []
  "The human has won the game!")

(defn computer-wins []
  "All hail the machine!")

(defn move-prompt-message []
  "Choose your fate")
