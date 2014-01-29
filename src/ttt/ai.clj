(ns ttt.ai
  (:require [ttt.rules :refer :all]
            [ttt.board :refer :all]))

(declare best-moves)

(defn key-for [scores best-score]
  (filter #(= (get scores %) best-score) (keys scores)))

(defn alternate-max-values [scores]
  (- (apply max scores)))

(defn best-score [scores]
  (apply max (vals scores)))

(defn determine-score [board turn opponent depth]
  (if (win? board turn) (- 10 depth)
  (if (win? board opponent) (- 0 10 depth) 0)))

(defn minimax [board turn depth]
  (let [opponent (change-turn turn)]
    (if (game-over? board)
    (determine-score board turn opponent depth)
    (alternate-max-values (map #(minimax
      (move board % opponent) opponent (inc depth))
      (best-moves board opponent))))))

(defn board-scores [board spaces turn]
  (map #(minimax (move board % turn) turn 0) spaces))

(defn all-moves [board turn]
  (let [spaces (empty-spaces board)]
  (zipmap spaces (board-scores board spaces turn))))

(defn best-moves [board turn]
  (let [scores (all-moves board turn)
        best-score (best-score scores)]
  (key-for scores best-score)))

(defn best-move [board]
  (rand-nth (best-moves board :computer)))
