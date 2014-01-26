(ns cli.cli-interface
  (:require [cli.cli-messages :refer :all]))

(defn cli-display-board [board markers]
  (println (display-board board markers)))

(defn cli-draw-message []
  (println (draw-message)))

(defn cli-human-win-message []
  (println (human-wins)))

(defn cli-computer-win-message []
  (println (computer-wins)))

(defn cli-prompt-for-move [board]
  (println (move-prompt-message))
  (read-string (read-line)))
