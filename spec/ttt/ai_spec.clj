(ns ttt.ai-spec
  (:require [speclj.core :refer :all]
            [ttt.ai :refer :all]))

(describe "AI"

  (describe "best-move"
    (it "returns random available integer"
      (should-contain (best-move [0 1 2]) [0 1 2]))))
