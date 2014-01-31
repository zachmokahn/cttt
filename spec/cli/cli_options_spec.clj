(ns cli.cli-options-spec
  (:require [speclj.core :refer :all]
            [cli.cli-options :refer :all]))

(describe "CLI OPTIONS SPEC"
  (describe "cli-game-play"
    (it "pvc returns one human & one computer"
        (should= { :player1 :player, :player2 :computer }
                 ( :pvc cli-game-play )))
    (it "pvp returns two human"
      (should= {:player1 :player, :player2 :player}
               ( :pvp cli-game-play )))
    (it "cvc returns two computer"
      (should= {:player1 :computer, :player2 :computer}
               ( :cvc cli-game-play ))))

  (describe "cli-computer-difficulty"
    (it "hard returns ttt.ai/best-move"
      (should= ttt.ai/best-move
               ( :hard cli-computer-difficulty )))
    (it "easy returns ttt.ai/random-move"
      (should= ttt.ai/random-move
               (:easy cli-computer-difficulty )))))
