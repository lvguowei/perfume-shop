(ns perfumeshop.core
  (:require [clojure.core.logic.fd :as fd]
            [clojure.core.logic :as logic]
            [clojure.string :as string])
  (:gen-class))

(defn parse-customer
  "Parse single customer string."
  [customer]
  (let [customer (rest (map read-string (string/split customer #" ")))]
    (partition 2 customer)))

(defn parse-customers
  "Parse customer strings into list structure.
  E.g. (((1 1)) ((1 0) (2 0)) ((5 0)))"
  [customers]
  (map parse-customer customers))

(defn customer-requirement
  "Build a goal from a single customer requirement."
  [req lvars]
  (let [form (dec (first req))
        type (second req)]
    (logic/== (nth lvars form) type)))

(defn customer-requirements
  [customer lvars]
  (map #(customer-requirement % lvars) customer))

(defn build-customers-goals
  "Build a list of goals from customer requirements."
  [customers lvars]
  (map #(logic/or* (customer-requirements % lvars)) customers))

(defn build-default-goals
  "Put non specified formulas to oil by default."
  [formula-number customers lvars]
  (let [whole (map inc (range formula-number))
        specified (set (map first (reduce concat customers)))
        unspecified (filter #(not (contains? specified %)) whole)]
    (map #(customer-requirement (list % 0) lvars) unspecified)))

(defn build-perfume-form-goals
  "Each formula is either 0 oil or 1 spray."
  [lvars]
  (map #(fd/in % (fd/interval 0 1)) lvars))

(defn run-testcase
  "Runs a testcase and return the optimal formula batches,
  nil if impossible."
  [testcase]
  (let [formula-number (read-string (first testcase))
        customer-number (read-string (second testcase))
        customers (parse-customers (subvec testcase 2))
        lvars (repeatedly formula-number logic/lvar)]
    (->> (logic/run* [q]
           (logic/== q lvars)
           (logic/and* (build-perfume-form-goals lvars))
           (logic/and* (build-customers-goals customers lvars))
           (logic/and* (build-default-goals formula-number customers lvars)))
         distinct
         (sort #(compare (reduce + %1) (reduce + %2)))
         first)))

(defn parse-testcases
  "Parse testcases out of lines.
  E.g. [[5 1 1 1 1] [1 2 1 1 0 1 1 1]]"
  [number lines]
  (loop [n number
         ls lines
         result []]
    (if (= 1 n)
      (conj result ls)
      (let [customer-number (read-string (second ls))]
        (recur (dec n)
               (subvec ls (+ customer-number 2))
               (conj result (subvec ls 0 (+ customer-number 2))))))))

(defn -main
  [& args]
  (if (nil? args)
    (println "Please give the input file.")
    (let [input-file (first args)
          input (slurp input-file)
          lines (string/split-lines input)
          testcase-number (read-string (first lines))
          lines (subvec lines 1)
          testcases (parse-testcases testcase-number lines)]
      (dotimes [i testcase-number]
        (let [result (run-testcase (get testcases i))]
          (print (format "Case #%d: " (inc i)))
          (if (nil? result)
            (println "IMPOSSIBLE")
            (println (string/join " " result))))))))
