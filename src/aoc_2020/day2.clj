(ns aoc-2020.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def input
  (line-seq (io/reader (io/resource "day2-input.txt"))))

(defn password-is-valid-part1
  [line]
  (let [char-key (get (second line) 0)
        timesToBeShown (str/split (first line) #"-")
        minimum-display (Integer/parseInt (first timesToBeShown))
        maximum-display (Integer/parseInt (second timesToBeShown))
        actually-display (get (frequencies (nth line 2)) char-key 0)]
    (and (>= actually-display minimum-display)
         (<= actually-display maximum-display))))

(def part1
  (time (count (filter true?
                       (map password-is-valid-part1
                            (map #(str/split % #" ") input))))))

(defn password-is-valid-part2
  [line]
  (let [char-key (get (second line) 0)
        positions-to-check (str/split (first line) #"-")
        first-position (dec (Integer/parseInt (first positions-to-check)))
        second-position (dec (Integer/parseInt (second positions-to-check)))
        password-field (nth line 2)]
    (bit-xor (if (= (get password-field first-position) char-key) 1 0)
             (if (= (get password-field second-position) char-key) 1 0))))

(def part2
  (time (count (filter #(= 1 %)
                       (map password-is-valid-part2
                            (map #(str/split % #" ") input))))))

(defn -main
  [& args]
  (print "Part1: " part1 "\nPart2: " part2))