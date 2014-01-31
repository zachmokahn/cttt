(ns ttt.ai-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]
            [ttt.ai :refer :all]))

(describe "TTT AI SPEC"
  (describe "algorithm"
    (it "returns 0 if its a winning move"
      (should= 0
      (best-move ["-" "o" "o"
                  "-" "-" "-"
                  "-" "-" "-"] :player2)))
    (it "returns 0 if its a blocking move"
      (should= 0
      (best-move ["-" "x" "x"
                  "-" "-" "-"
                  "-" "-" "-"] :player2)))
    (it "prevents a fork"
      (should-contain (best-move ["-" "-" "x"
                                 "-" "o" "-"
                                 "x" "-" "-"] :player2)
                      [1 3 5 7])))

  (describe "determine-score"
    (it "returns 10 if move is win for current-turn"
      (should= 10
      (determine-score ["o" "o" "o" "o" "o" "o" "o" "o" "o"] :player2 :player1 0)))
    (it "returns -10 if move is a win for opponent"
      (should= -10
      (determine-score ["o" "o" "o" "o" "o" "o" "o" "o" "o"] :player1 :player2 0)))
    (it "returns 0 if board is a draw"
      (should= 0
      (determine-score ["x" "o" "x" "x" "o" "x" "o" "x" "o"]:player1 :player2 0))))

  (describe "all-moves"
    (it "returns the moves with highest yield"
      (with-redefs [board-scores (fn [a b c] [3 2 1])]
        (should= {0 3, 1 2, 2 1}
        (all-moves ["-" "-" "-"] :player2)))))

  (describe "best-moves"
    (it "returns the indexes of higest value"
      (with-redefs [all-moves (fn [a b] {0 3, 1 3, 2 1})]
        (should= [0 1]
        (best-moves ["fake board"] :player2)))))

  (describe "best-move"
    (it "returns the index of higest value"
      (with-redefs [all-moves (fn [a b] {0 3, 1 2, 2 1})]
        (should= 0
        (best-move ["fake board"] :player2)))))

  (describe "random-move"
    (it "returns a random index from board"
      (should-contain (random-move ["-" "-"] :player2) [0,1]))))
