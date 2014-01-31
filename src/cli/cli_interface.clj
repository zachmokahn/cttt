(ns cli.cli-interface
  (:require [cli.cli-options :refer :all]
            [cli.cli-messages :refer :all])
      (:use [clojure.java.shell :only [sh]]))

(defn cli-clear-screen []
  (println (:out (sh "clear"))))

(defn cli-display-board [board]
  (println (display-board board)))

(defn cli-draw-message []
  (println (draw-message)))

(defn cli-win-message [player]
  (println (player-wins player)))

(defn cli-computer-win-message []
  (println (computer-wins)))

(defn cli-prompt-for-move [board]
  (println (move-prompt-message))
  (read-string (read-line)))

(defn cli-prompt-for-game-mode []
  (cli-clear-screen)
  (println (game-mode-message))
  (let [mode (read-string (read-line))]
    (cli.cli-options/determine-mode mode)))

(defn cli-prompt-for-difficulty []
  (cli-clear-screen)
  (println (game-difficulty-message))
  (let [difficulty (read-string (read-line))]
    (cli.cli-options/determine-difficulty difficulty)))

(defn get-difficulty [mode]
  (if (= mode :pvp) :easy
      (cli-prompt-for-difficulty)))

(defn cli-prompt-for-options []
  (let [mode        (cli-prompt-for-game-mode)
        difficulty  (get-difficulty mode) ]
   {:game-mode mode,
    :difficulty difficulty} ))
