(ns ttt.messages-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]
            [ttt.messages :refer :all]))

(let [x (:player marker)
      o (:computer marker)
      b (:empty marker)]

(describe "Messages Spec"
  (describe "render"
    (it "return value if occupied"
      (should= (format " %s " x)
               (render [x x] 1)))
    (it "returns index if empty"
      (should= " 1 "
               (render [b b] 1))))
  (describe "display-board"
    (it "shows the board properly"
        (should=(str
                " 0 | 1 | 2 \n"
                "-----------\n"
                " 3 | 4 | 5 \n"
                "-----------\n"
                " 6 | 7 | 8 ")
                (display-board [b b b b b b b b b])
                  )))
  (describe "displays correct messages"
    (it "human-wins"
      (should= "The human has won the game!"
               (human-wins)))
    (it "computer-wins"
      (should= "All hail the machine!"
               (computer-wins)))
    (it "move-prompt-message"
      (should= "Choose your fate"
               (move-prompt-message))))))
