(ns cli.cli-players
  (:require [ttt.ai :refer :all]
            [cli.cli-options :refer :all]
            [cli.cli-players :refer :all]
            [cli.cli-interface :refer :all]))

(def cli-human
  { :move cli-prompt-for-move })

(defn cli-computer [option]
  { :move (option cli-computer-difficulty) } )

(defn cli-players [option]
  { :player cli-human
    :computer (cli-computer option) })

(defn cli-change-turn [turn]
  (if (= turn :player1) :player2 :player1))
