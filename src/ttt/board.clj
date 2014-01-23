(ns ttt.board
  (:require [ttt.constants :refer :all]))

(def new-board
  (vec (repeat 9 (:empty marker))))

(defn move [board index turn]
  (assoc board index (turn marker)))

(defn empty-spaces [board]
  (keep-indexed (fn[index value]
    (if (= value (:empty marker)) index)) board))
