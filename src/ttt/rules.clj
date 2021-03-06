(ns ttt.rules
  (:require [ttt.constants :refer :all]))

(def winning-combos
  [[0 1 2] [3 4 5] [6 7 8]
   [0 3 6] [1 4 7] [2 5 8]
   [0 4 8] [2 4 6]])

(defn valid-move? [board index]
  (= (get board index) (:blank marker)))

(defn change-turn [turn]
  (if (= turn :player1) :player2 :player1))

(defn map-winning-combos [board token]
  (filter (fn [combo] (every? (fn [marker] (= marker token))
    (map (fn [index] (get board index)) combo))) winning-combos))

(defn get-tokens [token coll]
  (filter #(= token %) coll))

(defn get-blanks [coll]
  (filter #(= (:blank marker) %) coll))

(defn almost-won? [token coll]
  (and (= 1 (count (get-blanks coll)))
       (= 2 (count (get-tokens token coll)))))

(defn can-win? [token coll]
  (and (= 2 (count (get-blanks coll)))
       (= 1 (count (get-tokens token coll)))))

(defn almost-won-combos [board token]
  (filter (fn [combo]
    (almost-won? (token marker)
      (map #(get board %) combo))) winning-combos))

(defn can-win-combos [board token]
  (filter (fn [combo]
    (can-win? (token marker)
      (map #(get board %) combo))) winning-combos))

(defn win? [board turn]
  (not (empty? (map-winning-combos board (turn marker)))))

(defn any-winner? [board]
  (or (win? board :player1)
      (win? board :player2)))

(defn draw? [board]
  (and (not-any? (fn [value] (= value (:blank marker))) board)
       (not (any-winner? board))))

(defn game-over? [board]
  (or (draw? board) (any-winner? board)))
