(ns ttt.ai
  (:require [ttt.rules :refer :all]
            [ttt.board :refer :all]))

(declare best-moves)
(declare minimax)

(defn max-value [map]
  "calculates the higest value from a map"
  (apply max (vals map)))

(defn alternate-max-value [coll]
  "inverts the max value from a map"
  (- (apply max coll)))

(defn key-for [map value]
  "gets the key value of the map corresponding to the higest value"
  (filter #(= (get map %) value) (keys map)))

(defn next-best-moves [board opponent depth]
  "itterates through the minmax algorithm with the opponents token
   to determine the next best moves that could be played"
  (map #(minimax (move board % opponent) opponent (inc depth))
        (best-moves board opponent)))

(defn alternate-next-best-moves [board opponent depth]
  "alternates the max value as it winds back up the stack in
   order to fascilitate the theory that the best outcome for the
   opponent is the worst outcome for the player"
  (alternate-max-value (next-best-moves board opponent depth)))

(defn determine-score [board turn opponent depth]
  "gives value to the outcome of the board based on the number
   of turns it takes to reach that outcome"
  (if (win? board turn) (- 10 depth)
  (if (win? board opponent) (- 0 10 depth) 0)))

(defn board-scores [board spaces turn]
  "calculates the score of each available move"
  (map #(minimax (move board % turn) turn 0) spaces))

(defn all-moves [board turn]
  "generates a map where the key represents the index of
   the position and the value represents the score awarded
   to that position"
  (let [spaces (empty-spaces board)]
  (zipmap spaces (board-scores board spaces turn))))

(defn best-moves [board turn]
  "returns all of the keys that pertain to the highest score
   relative to the player's token"
  (let [scores (all-moves board turn)
        max-value (max-value scores)]
  (key-for scores max-value)))

(defn minimax [board turn depth]
  "either returns the point value for the move if the base case
   is met or alternates the turn and recurs until a resolution
   is reached"
  (let [opponent (change-turn turn)]
    (if (game-over? board)
    (determine-score board turn opponent depth)
    (alternate-next-best-moves board opponent depth))))

(defn best-move [board]
  "randomly chooses any of the move that have the highest success
   yield"
  (rand-nth (best-moves board :computer)))
