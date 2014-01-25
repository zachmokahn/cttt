(ns cli.cli-players
  (:require [ttt.ai :refer :all]
            [cli.cli-interface :refer :all]))

(def cli-human
  {:move cli-prompt-for-move})

(def cli-computer
  {:move best-move})

(def cli-players
  {:player cli-human
   :computer cli-computer})

