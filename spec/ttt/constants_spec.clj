(ns ttt.constants-spec
  (:require [speclj.core :refer :all]
            [ttt.constants :refer :all]))

(describe "Constant Spec"
  (describe "marker"
    (it ":player is 'x'"
      (should= "x"
               (:player marker)))
    (it ":computer is 'o'"
      (should= "o"
               (:computer marker)))
    (it ":blank is '-'"
      (should= "-"
               (:blank marker)))))
