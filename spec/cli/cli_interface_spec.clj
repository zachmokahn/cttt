(ns cli.cli-interface-spec
  (:require [speclj.core :refer :all]
            [cli.cli-interface :refer :all]))

(describe "CLI INTERFACE SPEC"
  (around [it]
    (with-out-str (it)))

 (describe "cli-game-options-prompt"
   (it "prints game options for game-mode"
     (with-redefs [cli.cli-messages/game-mode-message
     (fn [] "game-mode")]
        (should= "game-mode\n"
        (with-out-str
          (with-in-str "2"
            (cli-prompt-for-game-mode))))))

    (it "returns game-mode key"
      (with-redefs [cli.cli-messages/game-mode-message
      (fn [] "")]
        (should= :pvc
          (with-in-str "1"
            (cli-prompt-for-game-mode)))
        (should= :pvp
          (with-in-str "2"
            (cli-prompt-for-game-mode)))
        (should= :cvc
          (with-in-str "3"
             (cli-prompt-for-game-mode)))
        (should= :pvc
          (with-in-str "banana"
              (cli-prompt-for-game-mode))))))

  (describe "cli-prompt-for-difficulty"
   (it "prints game options for difficulty"
     (with-redefs [cli.cli-messages/game-difficulty-message
     (fn [] "game difficulty")]
        (should= "game difficulty\n"
        (with-out-str
          (with-in-str "2"
            (cli-prompt-for-difficulty))))))

    (it "returns game-mode key"
      (with-redefs [cli.cli-messages/game-mode-message
      (fn [] "")]
        (should= :hard
          (with-in-str "1"
            (cli-prompt-for-difficulty)))
        (should= :easy
          (with-in-str "2"
            (cli-prompt-for-difficulty)))
        (should= :easy
          (with-in-str "banana"
            (cli-prompt-for-difficulty))))))
 
  (describe "cli-display-board"
    (it "prints board to command line"
      (with-redefs [cli.cli-messages/display-board
      (fn [board] "board here")]
        (should= "board here\n"
          (with-out-str(
            cli-display-board ["board"]))))))

  (describe "human-win-message"
    (it "prints message to command line"
      (with-redefs [cli.cli-messages/player-wins
      (fn [player] "player wins")]
        (should= "player wins\n"
          (with-out-str
            (cli-win-message "player"))))))

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
              (cli-prompt-for-move ["board"])))))))
