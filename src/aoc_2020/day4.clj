(ns aoc-2020.day4
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def input
  (slurp (io/reader (io/resource "day4-input.txt"))))

(def required-fields
  ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])


;;part-1
(defn passport-is-valid-part1
  [passport]
  (every? #(str/includes? passport %) required-fields))

(defn valid-passports-part1
  [passports]
  (count (filter passport-is-valid-part1 passports)))

;;part-2

(defn not-nil?
  [value]
  (comp not nil?) value)

(defn birth-year-is-valid?
  [year]
  (and (not-nil? year) (>= (Integer/parseInt year) 1920) (<= (Integer/parseInt year) 2002)))

(defn issue-year-is-valid?
  [year]
  (and (not-nil? year) (>= (Integer/parseInt year) 2010) (<= (Integer/parseInt year) 2020)))

(defn expiration-year-is-valid?
  [year]
  (and (not-nil? year) (>= (Integer/parseInt year) 2020) (<= (Integer/parseInt year) 2030)))

(defn height-in-cm-is-valid?
  [height]
  (let [height-value (Integer/parseInt (re-find #"^\d+" height))]
    (and (>= height-value 150) (<= height-value 193))))

(defn height-in-inches-is-valid?
  [height]
  (let [height-value (Integer/parseInt (re-find #"^\d+" height))]
    (and (>= height-value 59) (<= height-value 76))))

(defn height-is-valid?
  [height]
  (and (not-nil? height)
       (cond
         (str/includes? height "cm") (height-in-cm-is-valid? height)
         (str/includes? height "in") (height-in-inches-is-valid? height)
         :else false)))

(def valid-eyes-colors
  ["amb" "blu" "brn" "gry" "grn" "hzl" "oth"])

(defn eye-color-is-valid?
  [color]
  (and (not-nil? color)
       (some #(str/includes? color %) valid-eyes-colors)) )

(defn pid-is-valid?
  [pid]
  (and (= 9 (count pid))
       (number? (Integer/parseInt pid))))

(defn hair-color-is-valid?
  [color]
  (and (= 7 (count color))
       (not-empty (re-find #"^#([0-9a-f]{6})$" color))))

(defn parse-entry-passport
  [entry]
  (into {} (map (comp vec next)) (re-seq #"(\w{3}):(\S+)" entry)))

(defn passport-is-valid-part2
  [{:strs [byr iyr eyr hgt hcl ecl pid]}]
  (and (birth-year-is-valid? byr)
          (issue-year-is-valid? iyr)
          (expiration-year-is-valid? eyr)
          (height-is-valid? hgt)
          (hair-color-is-valid? hcl)
          (eye-color-is-valid? ecl)
          (pid-is-valid? pid)))

(defn valid-passports-part2
  [passports]
  (count (filter passport-is-valid-part2 (map parse-entry-passport passports))))


(defn -main
  [& args]
  (println "Part1:" (time (valid-passports-part1 (str/split input #"\n\n")))
         "Part2:" (time (valid-passports-part2 (str/split input #"\n\n")))))