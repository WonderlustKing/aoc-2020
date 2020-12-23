(ns aoc-2020.day3
  (:require [clojure.java.io :as io])
  (:gen-class))

(def input
  (line-seq (io/reader (io/resource "day3-input.txt"))))

(defn find-trees
  [path move-right]
  (loop [trees 0
         index move-right
         remaining-lines path]
    (if (empty? remaining-lines)
      trees
      (let [[line & remaining] remaining-lines
            line-size (count line)
            index (mod index line-size)]
        (recur (if (= \# (get line index))
                 (inc trees)
                 trees)
               (+ move-right index)
               remaining)))))


(defn -main
  [& args]
  (print "Part1: " (time (find-trees (rest input) 3))
         "Part2: " (time (* (find-trees (rest input) 1)
                            (find-trees (rest input) 3)
                            (find-trees (rest input) 5)
                            (find-trees (rest input) 7)
                            (find-trees (take-nth 2 (drop 2 input)) 1)))))

