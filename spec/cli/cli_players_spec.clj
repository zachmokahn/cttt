(ns cli.cli-players-spec
  (:require [speclj.core :refer :all]
            [cli.cli-players :refer :all]))

(describe "CLI PLAYERS SPEC"
  (describe "players"
  (it ":player returns cli-player"
    (should= cli-human
             (:player (cli-players :test))))
  (it ":computer returns cli-computer"
    (should= (cli-computer :test)
             (:computer (cli-players :test))))
  (it "successfully passes options"
    (should= {:move ttt.ai/random-move }
             (:computer (cli-players :easy)))))

(describe "move"
  (describe "player"
    (it "prompts for move"
      (should= cli.cli-interface/cli-prompt-for-move
               (:move cli-human))))

  (describe "computer"
    (it "delegates :hard to best-move"
      (should= ttt.ai/best-move
               ( :move (cli-computer :hard))))
    (it "delegates :easy to random-move"
      (should= ttt.ai/random-move
               (:move (cli-computer :easy)))))))
