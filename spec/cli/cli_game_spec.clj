(ns cli.cli-game-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]
            [cli.cli-game :refer :all]))

(let [b (:blank marker)
      o (:player2 marker)
      x (:player1 marker)
      pvp { :player1 :player :player2 :player }
      pvc { :player1 :player :player2 :computer }
      cvc { :player1 :computer :player2 :computer }
      available-board [x x x x x x x x b]
      draw [x o x x o x o x o]
      board-x [x x x x x x x x x]
      board-o [o o o o o o o o o]]

(describe "CLI GAME SPEC"

  (describe "get-winner"
    (it "calls computer-win for computer wins"
      (with-redefs [cli.cli-interface/cli-computer-win-message
                      (fn [] "computer-win")]
       (should= "computer-win"
                (get-winner board-x cvc))
       (should= "computer-win"
                (get-winner board-o cvc))))

    (it "calls win-message for player wins"
      (with-redefs [cli.cli-interface/cli-win-message
                    (fn [name] name)]
        (should= "Player 1"
                 (get-winner board-x pvp))
        (should= "Player 2"
                 (get-winner board-o pvp)))))

  (describe "get-results"
    (it "calls cli-draw-message for draw"
      (with-redefs [cli.cli-interface/cli-draw-message
                    (fn [] "draw")]
        (should= "draw"
                 (get-results draw pvp))))

    (it "calls get-winner if game is won"
      (with-redefs [get-winner (fn [board players]
                                  "get winner")]
        (should= "get winner"
                 (get-results board-x pvp))
        (should= "get winner"
                 (get-results board-o pvp)))))

  (describe "get-move"
    (around [it]
      (with-out-str (it)))
    (it "calls best-move -> :hard :computer"
      (should= (ttt.ai/best-move available-board :player2)
                 (get-move available-board :player2 pvc :hard)))
    (it "calls random-move -> :easy :computer"
      (should= (ttt.ai/random-move available-board :player2)
               (get-move available-board :player2 pvc :easy)))
    (it "calls cli-prompt -> :player"
      (with-redefs [cli.cli-interface/cli-prompt-for-move
                    (fn [board] "prompt")]
      (should= "prompt"
               (get-move available-board :player1 pvc :easy)))))))
