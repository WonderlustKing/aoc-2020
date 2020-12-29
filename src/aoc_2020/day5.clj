(ns aoc-2020.day5
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def input
  (line-seq (io/reader (io/resource "day5-input.txt"))))

(defn find-halfs
  [letter min-num max-num]
  (cond
    (or (= letter "L") (= letter "F")) (vector min-num (quot (+ min-num max-num) 2))
    (or (= letter "R") (= letter "B")) (vector (inc (quot (+ min-num max-num) 2)) max-num)
    :else (vector min-num max-num)
    ))

(defn find-position
  [seat min-num max-num]
  (reduce (fn [[min max] char]
            (if (= 1 (- max min))
              (if (or (= char "F") (= char "L")) min max)
              (find-halfs char min max)))
            [min-num max-num]
            (str/split seat #"")))

(defn find-row
  [seat]
  (find-position seat 0 127))

(defn find-column
  [last-three-chars]
  (find-position last-three-chars 0 7))

(defn find-seat-id
  [seat]
  (let [row-chars (subs seat 0 (- (count seat) 3))
        column-chars (str/replace seat row-chars "")]
    (+ (* 8 (find-row row-chars)) (find-column column-chars))))

(defn -main
  [& args]
  (print "Part1: " (time (apply max (map find-seat-id input)))))