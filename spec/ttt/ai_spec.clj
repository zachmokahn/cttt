(ns ttt.ai-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]
            [ttt.ai :refer :all]))

(describe "AI"
  (describe "algorithm"
    (it "returns 0 if its a winning move"
      (should= 0
      (best-move ["-" "o" "o"
                  "-" "-" "-"
                  "-" "-" "-"])))
    (it "returns 0 if its a blocking move"
      (should= 0
      (best-move ["-" "x" "x"
                  "-" "-" "-"
                  "-" "-" "-"])))
    (it "prevents a fork"
      (should-contain (best-move ["-" "-" "x"
                                 "-" "o" "-"
                                 "x" "-" "-"])
                      [1 3 5 7])))
  (describe "determine-score"
    (it "returns 1 if move is win for current-turn"
      (should= 10
      (determine-score ["o" "o" "o" "o" "o" "o" "o" "o" "o"] :computer :player 0)))
    (it "returns -1 if move is a win for opponent"
      (should= -10
      (determine-score ["o" "o" "o" "o" "o" "o" "o" "o" "o"] :player :computer 0)))
    (it "returns 0 if board is a draw"
      (should= 0
      (determine-score ["x" "o" "x" "x" "o" "x" "o" "x" "o"]:player :computer 0))))

    (describe "all-moves"
      (it "returns the moves with highest yield"
        (with-redefs [board-scores (fn [a b c] [3 2 1])]
        (should= {0 3, 1 2, 2 1}
        (all-moves ["-" "-" "-"] :computer)))))

  (describe "best-moves"
      (it "returns the indexes of higest value"
        (with-redefs [all-moves (fn [a b] {0 3, 1 3, 2 1})]
        (should= [0 1]
        (best-moves ["fake board"] :computer)))))

  (describe "best-move"
      (it "returns the index of higest value"
        (with-redefs [all-moves (fn [a b] {0 3, 1 2, 2 1})]
        (should= 0
        (best-move ["fake board"]))))))
