(ns ttt.ai
  (:require [ttt.rules :refer :all]
            [ttt.board :refer :all]))

(declare best-moves)
(declare minimax)

(defn max-value [map]
  (apply max (vals map)))

(defn alternate-max-value [coll]
  (- (apply max coll)))

(defn key-for [map value]
  (filter #(= (get map %) value) (keys map)))

(defn next-best-moves [board opponent depth alpha beta]
  (map #(minimax (move board % opponent) opponent (inc depth) (- beta) (- alpha))
       (empty-spaces board)))

(defn get-better-moves [board opponent depth alpha beta]
  (take-while #(< % beta) (next-best-moves board opponent depth alpha beta)))

(defn best-move-list [board opponent depth alpha beta]
  (cons alpha (get-better-moves board opponent depth alpha beta)))

(defn alternate-next-best-moves [board opponent depth alpha beta]
  (alternate-max-value (best-move-list board opponent depth alpha beta)))

(defn evaluate-board [board turn]
  (let [opponent (change-turn turn)]
  (+ (* 0.25 (- (count (almost-won-combos board turn))
                (count (almost-won-combos board opponent))))
     (* 0.125 (- (count (can-win-combos board turn))
                 (count (can-win-combos board opponent)))))))

(defn determine-score [board turn depth]
  (if (win? board turn) (- 3 depth)
      (evaluate-board board turn)))

(defn board-scores [board spaces turn]
  (map #(minimax (move board % turn) turn 0 -10 10) spaces))

(defn all-moves [board turn]
  (let [spaces (empty-spaces board)]
  (zipmap spaces (board-scores board spaces turn))))

(defn best-moves [board turn]
  (let [scores (all-moves board turn)
        max-value (max-value scores)]
  (key-for scores max-value)))

(defn best-move [board turn]
  (rand-nth (best-moves board turn)))

(defn random-move [board turn]
  (rand-nth (empty-spaces board)))

(defn minimax [board turn depth alpha beta]
  (let [opponent (change-turn turn)]
    (if (or (= depth 2) (game-over? board))
    (determine-score board turn depth)
    (alternate-next-best-moves board opponent depth alpha beta))))
