(ns ttt.cli-spec
  (:require [speclj.core :refer :all]
            [ttt.cli :refer :all]))

(describe "CLI Spec"
  (around [it]
    (with-out-str (it)))
  (describe "display"
    (it "prints board to command line"
      (with-redefs [ttt.messages/display-board (fn [board] "board here")]
        (should= "board here" (display ["a"])))))
  (describe "human-win-message"
    (it "prints message to command line"
      (with-redefs [ttt.messages/human-wins (fn [] "human wins")]
        (should= "human wins" (human-win-message)))))
  (describe "computer-win-message"
    (it "prints message to command line"
      (with-redefs [ttt.messages/computer-wins (fn [] "computer wins")]
        (should= "computer wins" (computer-win-message)))))
  (describe "prompt-for-move"
    (it "prints message to command line"
      (with-redefs [ttt.messages/move-prompt-message 
        (fn [] "select index")]
          (should= "select index\n"
                    (with-out-str(with-in-str "2" (prompt-for-move))))))
    (it "reads string returns index"
      (with-redefs [ttt.messages/move-prompt-message (fn [] "")]
        (should= 0
          (with-in-str "0" (prompt-for-move)))))))
