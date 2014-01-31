(ns ttt.constants-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]))

(describe "TTT CONSTANTS SPEC"
  (describe "marker"
    (it ":player1 is 'x'"
      (should= "x"
               (:player1 marker)))
    (it ":player2 is 'o'"
      (should= "o"
               (:player2 marker)))
    (it ":blank is '-'"
      (should= "-"
               (:blank marker)))))
