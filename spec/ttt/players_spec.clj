(ns ttt.players-spec
  (:require [speclj.core :refer :all]
            [ttt.players :refer :all]))

(describe "Players Spec"
  (describe "has two players"
    (it ":player returns human-player"
      (should= human-player
               (:player players)))
    (it ":computer returns computer-player"
      (should= computer-player
               (:computer players))))
  (describe "win-message"
    (it "delegates human win-message to ui"
      (should= ttt.cli/human-win-message
               (:win-message human-player)))
    (it "delegates computer win-message to ui"
      (should= ttt.cli/computer-win-message
               (:win-message computer-player))))
  (describe "move"
    (it "delegates computer move to ai"
      (should= ttt.ai/best-move
               (:move computer-player)))
    (it "delegates player move to cli"
      (should= ttt.cli/prompt-for-move
               (:move human-player)))))
