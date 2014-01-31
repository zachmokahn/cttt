(ns ttt.board-spec
  (:require [speclj.core :refer :all]
            [ttt.board :refer :all]
            [ttt.constants :refer :all]))

(let [x (:player1 marker)
      o (:player2 marker)
      b (:blank marker)
      board [b b b b b b b b b]]

(describe "TTT BOARD SPEC"

  (describe "new-board"
    (it "generates an empty board"
      (should= new-board
               board)))

  (describe "move"
    (it "returns new vector with changed index"
      (should= [x b b b b b b b b]
               (move new-board 0 :player1))))

  (describe "empty-spaces"
    (it "returns seq of available space indexes"
      (should= [0 1 2 3 4 5 6 7 8]
               (empty-spaces new-board))
      (should= [1 2 3]
               (empty-spaces [x b b b]))
      (should= []
               (empty-spaces [o o o]))))))
