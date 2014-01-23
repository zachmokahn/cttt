(ns ttt.players
  (:require [ttt.cli :refer :all]
            [ttt.ai :refer :all]))

(def human-player
  {:win-message human-win-message
   :move prompt-for-move})

(def computer-player
  {:win-message computer-win-message
   :move best-move})

(def players
  {:player human-player
   :computer computer-player})
