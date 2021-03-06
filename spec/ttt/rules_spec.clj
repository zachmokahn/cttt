(ns ttt.rules-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]
            [ttt.rules :refer :all]))

(let [b (:blank marker)
      x (:player1 marker)
      o (:player2  marker)
      new-board [b b b b b b b b b]
     draw-board [o x o x o x x o x]
    x-win-board [x x x b b b b b b]
    o-win-board [o o o b b b b b b]]

(describe "TTT RULES SPEC"
  (describe "valid-move?"
    (it "returns true if the space is empty"
      (should= true
               (valid-move? new-board 0)))
    (it "returns false if the space is occupied"
      (should= false
               (valid-move? o-win-board 0))
      (should= false
               (valid-move? x-win-board 0))))

  (describe "change-turn"
    (it ":computer returns :player"
      (should= :player1
               (change-turn :player2)))
    (it ":player returns :computer"
      (should= :player1
               (change-turn :player2))))

(describe "win?"
    (it "returns false if there is no winner"
        (should= false
                 (win? new-board :player1))
        (should= false
                 (win? new-board :player2))
        (should= false
                 (win? draw-board :player1))
        (should= false
                 (win? draw-board :player2)))
    (it "returns true if :player wins"
        (should= true
          (win? x-win-board :player1)))
    (it "returns true if :computer wins"
        (should= true
          (win? o-win-board :player2))))

  (describe "#almost-won-combos"
    (it "returns indexes of almost winnable combos"
      (should-contain [0 1 2]
                      (almost-won-combos
                        ["x" "x" "-"
                         "-" "-" "-"
                         "-" "-" "-"] :player1))
      (should-contain [0 4 8]
                      (almost-won-combos
                        ["o" "-" "-"
                         "-" "o" "-"
                         "-" "-" "-"] :player2))))

  (describe "#can-win-combos"
    (it "returns indexes of possible winnable combos"
      (should-contain [0 1 2]
                      (can-win-combos
                        ["x" "-" "-"
                         "-" "-" "-"
                         "-" "-" "-"] :player1))))

  (describe "draw?"
    (it "returns false if the game is not over"
        (should= false
                 (draw? new-board)))
    (it "returns true if the game is a draw"
        (should= true
                 (draw? draw-board)))
    (it "returns false if there is a winner"
        (should= false
                 (draw? [o x o x o x o x o]))))

  (describe "game-over?"
    (it "returns true if there is a draw"
      (should= true
               (game-over? draw-board)))
    (it "returns true if there is a winner"
      (should= true
               (game-over? x-win-board))
      (should= true
               (game-over? o-win-board)))
    (it "returns false if no winner and no draw"
      (should= false
               (game-over? new-board))))))
