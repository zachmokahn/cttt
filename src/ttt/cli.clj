(ns ttt.cli
  (:require [ttt.messages :refer :all]))

(defn human-win-message []
  println (human-wins))

(defn computer-win-message []
  println (computer-wins))

(defn prompt-for-move []
  (println (move-prompt-message))
  (read-string (read-line)))
