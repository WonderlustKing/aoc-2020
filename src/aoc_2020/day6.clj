(ns aoc-2020.day6
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def input
  (slurp (io/reader (io/resource "day6-input.txt"))))

(defn anyone-answer-yes
  [group-of-answers]
  (count (disj (set group-of-answers) \newline)))

(defn everyone-answers-yes
  [group-of-answers]
  (let [answers-map (frequencies group-of-answers)
        yes-num (inc (get answers-map \newline 0))]
      (count
        (keep #(when (= (val %) yes-num)
                      (key %))
              answers-map))))

(defn -main
  [& args]
  (println "Part1:" (time (reduce + (map #(anyone-answer-yes %)
                                         (str/split input #"\R\R"))))
           "Part2:" (time (reduce + (map #(everyone-answers-yes %)
                                         (str/split input #"\R\R"))))))