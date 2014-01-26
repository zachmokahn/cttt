(ns cli.cli-players
  (:require [cli.cli-interface :refer :all]
            [ttt.ai :only :best-move]))

(def cli-human
  {:move cli-prompt-for-move})

(def cli-computer
  {:move ttt.ai/best-move})

(def cli-players
  {:player cli-human
   :computer cli-computer})

