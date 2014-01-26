(ns cli.cli-messages-spec
  (:require [speclj.core :refer :all]
            [cli.cli-messages :refer :all]))

(let [marker {:player "x"
              :computer "o"
              :blank "-"}
      x (:player marker)
      o (:computer marker)
      b (:blank marker)]

(describe "Messages Spec"
  (describe "render"
    (it "return value if occupied"
      (should= (format " %s " x)
               (render [x x] 1 marker)))
    (it "returns index if empty"
      (should= " 1 "
               (render [b b] 1 marker))))
  (describe "display-board"
    (it "shows the board properly"
        (should=(str
                " 0 | 1 | 2 \n"
                "-----------\n"
                " 3 | 4 | 5 \n"
                "-----------\n"
                " 6 | 7 | 8 ")
                (display-board [b b b b b b b b b] marker)
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
