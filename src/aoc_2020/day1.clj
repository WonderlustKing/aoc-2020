(ns aoc-2020.day1
  (:require [clojure.java.io :as io ])
  (:gen-class))

(def input
  (map #(Integer/parseInt %)
                (line-seq (io/reader (io/resource "day1-input.txt")))))

(def part1
  (set
    (for [x input
          y input
          :let [result (+ x y)]
          :when (= result 2020)]
      (* x y))))

(def part2
  (set
    (for [x input
          y input
          z input
          :let [result (+ x y z)]
          :when (= result 2020)]
      (* x y z))))

(defn -main
  [& args]
  (print "Part1: " part1 "\n" "Part2: " part2))