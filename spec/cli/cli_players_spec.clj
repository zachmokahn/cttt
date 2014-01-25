(ns cli.cli-players-spec
  (:require [speclj.core :refer :all]
            [cli.cli-players :refer :all]))

(describe "has two players"
  (it ":player returns cli-player"
    (should= cli-human
      (:player cli-players)))
  (it ":computer returns cli-computer"
    (should= cli-computer
      (:computer cli-players))))
(describe "move"
  (it "calls prompt functino for player move"
    (should= cli.cli-interface/cli-prompt-for-move
             (:move cli-human)))
  (it "delegates computer move to ai"
    (should= ttt.ai/best-move
             (:move cli-computer))))
