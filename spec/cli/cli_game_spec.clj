(ns cli.cli-game-spec
  (:require [speclj.core :refer :all]
            [cli.cli-game :refer :all]))
(describe "CLI GAME SPEC"
(describe "get-winner"
  (let [board-x ["x" "x" "x" "x" "x" "x" "x" "x" "x"]
        board-o ["o" "o" "o" "o" "o" "o" "o" "o" "o"]
        players {:player1 :computer :player2 :computer}]
  (it "calls computer-win for computer wins"
    (with-redefs [cli.cli-interface/cli-computer-win-message
                    (fn [] "computer-win")]
     (should= "computer-win"
              (get-winner board-x players))
     (should= "computer-win"
              (get-winner board-o players)))))
  (let [board-x ["x" "x" "x" "x" "x" "x" "x" "x" "x"]
        board-o ["o" "o" "o" "o" "o" "o" "o" "o" "o"]
        players {:player1 :player :player2 :player}]
  (it "calls win-message for player wins"
    (with-redefs [cli.cli-interface/cli-win-message
                  (fn [name] name)]
      (should= "Player 1"
               (get-winner board-x players))
      (should= "Player 2"
               (get-winner board-o players))))))

(describe "get-results"
  (let [board ["o" "x" "o" "o" "x" "o" "x" "o" "x"]
        players {:player1 :player :player2 :player}]
  (it "calls cli-draw-message for draw"
    (with-redefs [cli.cli-interface/cli-draw-message
                  (fn [] "draw")]
      (should= "draw"
               (get-results board players)))))
  (let [board-x ["x" "x" "x" "x" "x" "x" "x" "x" "x"]
        board-o ["o" "o" "o" "o" "o" "o" "o" "o" "o"]
        players {:player1 :player :player2 :player}]
  (it "calls get-winner if game is won"
    (with-redefs [get-winner (fn [board players]
                                "get winner")]
      (should= "get winner"
               (get-results board-x players))
      (should= "get winner"
               (get-results board-o players))))))

(describe "get-move"
  (let [board ["x" "x" "x" "x" "x" "x" "x" "x" "-"]
        turn :player2
        difficulty :hard
        players {:player1 :player :player2 :computer}]
  (it "calls best-move -> :hard :computer"
    (should= (ttt.ai/best-move board turn)
             (get-move board turn players difficulty))))
  (let [board ["x" "x" "x" "x" "x" "-" "x" "x" "x"]
        turn :player2
        difficulty :easy
        players {:player1 :player :player2 :computer}]
  (it "calls random-move -> :easy :computer"
    (should= (ttt.ai/random-move board turn)
             (get-move board turn players difficulty))))
  (let [board ["x" "x" "x" "x" "x" "-" "x" "x" "x"]
        turn :player1
        difficulty :easy
        players {:player1 :player :player2 :computer}]
  (it "calls cli-prompt -> :easy :player"
    (with-redefs [cli.cli-interface/cli-prompt-for-move
                  (fn [board] "prompt")]
    (should= "prompt"
             (get-move board turn players difficulty)))))))
