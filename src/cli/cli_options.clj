(ns cli.cli-options
  (:require [ttt.ai :refer :all]))

(def cli-game-play
  { :pvc { :player1 :player,   :player2 :computer }
    :pvp { :player1 :player,   :player2 :player }
    :cvc { :player1 :computer, :player2 :computer }})

(def cli-computer-difficulty
  { :hard best-move
    :easy random-move })

(defn determine-mode [mode]
  (if (= mode 1) :pvc
  (if (= mode 2) :pvp
  ;; Top Secret Mode
  (if (= mode 3) :cvc :pvc))))

(defn determine-difficulty [difficulty]
 (if (= difficulty 1) :hard
 (if (= difficulty 2) :easy :easy)))
