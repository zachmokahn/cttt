(ns cli.cli-interface-spec
  (:require [speclj.core :refer :all]
            [cli.cli-interface :refer :all]))

(let [marker {:blank "-"
              :player "x"
              :computer "o"}]
(describe "CLI Spec"
  (around [it]
    (with-out-str (it)))

  (describe "cli-display-board"
    (it "prints board to command line"
      (with-redefs [cli.cli-messages/display-board
      (fn [board marker] "board here")]
        (should= "board here\n"
          (with-out-str(
            cli-display-board ["board"] marker)))))

  (describe "human-win-message"
    (it "prints message to command line"
      (with-redefs [cli.cli-messages/human-wins
      (fn [] "human wins")]
        (should= "human wins\n"
          (with-out-str
            (cli-human-win-message))))))

  (describe "computer-win-message"
    (it "prints message to command line"
      (with-redefs [cli.cli-messages/computer-wins
      (fn [] "computer wins")]
        (should= "computer wins\n"
          (with-out-str
            (cli-computer-win-message))))))

  (describe "prompt-for-move"

    ;;; Displays Prompt
    (it "prints prompt to command line"
      (with-redefs [cli.cli-messages/move-prompt-message
        (fn [] "select index")]
          (should= "select index\n"
            (with-out-str
              (with-in-str "2"
                (cli-prompt-for-move ["board"]))))))

    ;;; Convert Input
    (it "reads string returns index"
      (with-redefs [cli.cli-messages/move-prompt-message 
        (fn [] "select index")]
          (should= 0
            (with-in-str "0"
              (cli-prompt-for-move ["board"])))))))))
