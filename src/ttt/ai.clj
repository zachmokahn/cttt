(ns ttt.ai
  (:require [ttt.rules :refer :all]
            [ttt.board :refer :all]))

(declare best-moves)
(declare minimax)

(defn max-value [map]
  "discovers the higest value from a map"
  (apply max (vals map)))

(defn alternate-max-value [coll]
  "inverts the max value from a collection"
  (- (apply max coll)))

(defn key-for [map value]
  "gets the key values from a map corresponding to the given value"
  (filter #(= (get map %) value) (keys map)))

(defn next-best-moves [board opponent depth alpha beta]
  "itterates through the minmax algorithm with the 'opponents' token
   to determine the next best moves that can be played (inverts the
   alpha and beta to fascilitate the alternating turns)."
  (map #(minimax (move board % opponent) opponent (inc depth) (- beta) (- alpha))
       (empty-spaces board)))

(defn get-better-moves [board opponent depth alpha beta]
  "gets all the moves with a better yield than 'beta' (minimum best move),
  it stops processing after the first move it comes across isn't 'better.'"
  (take-while #(< % beta) (next-best-moves board opponent depth alpha beta)))

(defn best-move-list [board opponent depth alpha beta]
  "prepends the 'alpha' (maximum best move), to the list of generated best moves"
  (cons alpha (get-better-moves board opponent depth alpha beta)))

(defn alternate-next-best-moves [board opponent depth alpha beta]
  "inverts the max value as it winds back up the stack in
   order to fascilitate the theory that the best outcome for the
   opponent is the worst outcome for the player"
  (alternate-max-value (best-move-list board opponent depth alpha beta)))

(defn determine-score [board turn depth]
  "gives value to the outcome of the board based on the number
   of turns it take to be resolved"
  (if (win? board turn) (- 10 depth)
  (+ (- 6 (count (almost-won-combos board (change-turn turn))))
     (count (almost-won-combos board turn)))))

(defn determine-winability [board turn depth]
  (if (winnable? board turn) (- 9 depth) 0))

(defn board-scores [board spaces turn]
  "calculates the score of each available move"
  (map #(minimax (move board % turn) turn 0 -10 10) spaces))

(defn all-moves [board turn]
  "generates a map where the key represents the index of
   the position with it's coresponding score"
  (let [spaces (empty-spaces board)]
  (zipmap spaces (board-scores board spaces turn))))

(defn best-moves [board turn]
  "returns all of the keys that pertain to the highest score
   relative to the player"
  (let [scores (all-moves board turn)
        max-value (max-value scores)]
  (key-for scores max-value)))

(defn best-move [board turn]
  "randomly chooses any of the moves that have the highest success
   yield"
  (rand-nth (best-moves board turn)))

(defn random-move [board turn]
  "randomly chooses an available index"
  (rand-nth (empty-spaces board)))

(defn minimax [board turn depth alpha beta]
  "it either returns the point value for the move if the base case
   is met (game over) or it alternates the turn and recurs until a
   resolution is met that is within the alpha-beta range"
  (let [opponent (change-turn turn)]
    (if (game-over? board)
    (determine-score board turn depth)
    (if (= depth 2)
    (determine-winability board turn depth)
    (alternate-next-best-moves board opponent depth alpha beta)))))
