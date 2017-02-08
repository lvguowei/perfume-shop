(ns perfumeshop.core-test
  (:require [clojure.test :refer :all]
            [perfumeshop.core :refer :all]))

(deftest test-parse-testcases
  (testing "should parse one testcase"
    (let [result (parse-testcases 1 ["1" "2" "1 1 0" "1 1 1"])]
      (is (= result [["1" "2" "1 1 0" "1 1 1"]]))))
  (testing "should parse multiple testcases"
    (let [result (parse-testcases 2 ["5" "3" "1 1 1" "2 1 0 2 0" "1 5 0" "1" "2" "1 1 0" "1 1 1"])]
      (is (= result [["5" "3" "1 1 1" "2 1 0 2 0" "1 5 0"]
                     ["1" "2" "1 1 0" "1 1 1"]])))))

(deftest test-parse-customers
  (testing "should parse single customer"
    (let [result (parse-customer "3 1 0 2 0 3 1")]
      (is (= result '((1 0) (2 0) (3 1))))))
  (testing "should parse muliple customers"
    (let [result (parse-customers ["1 1 1" "2 1 0 2 0"])]
      (is (= result '(((1 1)) ((1 0) (2 0))))))))

(deftest test-run-testcase
  (testing "#color: 1, #customer: 2, customer1: 1 0, customer2: 1 1"
    (let [result (run-testcase ["1" "2" "1 1 0" "1 1 1"])]
      (is (nil? result))))
  (testing "#color: 5, #customer: 3, customer1: 1 1, customer2: 1 0 2 0, customer3: 5 0"
    (let [result (run-testcase ["5" "3" "1 1 1" "2 1 0 2 0" "1 5 0"])]
      (is (= result '(1 0 0 0 0))))))
