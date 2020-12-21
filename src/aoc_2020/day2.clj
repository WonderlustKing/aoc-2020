(ns aoc-2020.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def input
  (line-seq (io/reader (io/resource "day2-input.txt"))))

(defn password-is-valid
  [line]
  (let [char-key (get (second line) 0)
        timesToBeShown (str/split (first line) #"-")
        minimum-display (Integer/parseInt (first timesToBeShown))
        maximum-display (Integer/parseInt (second timesToBeShown))
        actually-display (get (frequencies (nth line 2)) char-key 0)]
    (and (>= actually-display minimum-display)
         (<= actually-display maximum-display))))

(def part1
  (count (filter true?
                 (map password-is-valid
                      (map #(str/split % #" ") input)))))

(defn -main
  [& args]
  (print "Part1: " part1))