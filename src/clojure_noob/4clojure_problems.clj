;; Nothing but the truth
;; http://www.4clojure.com/problem/1

(= true true)

;; Simple math
;; http://www.4clojure.com/problem/2#prob-title

(= (- 10 (* 2 3)) 4)

;; Intro to strings
;; http://www.4clojure.com/problem/3#prob-title

(= "HELLO WORLD" (.toUpperCase "hello world"))

;; Intro to lists
;; http://www.4clojure.com/problem/4#prob-title

(= (list :a :b :c) '(:a :b :c))

;; Lists - Conj
;; http://www.4clojure.com/problem/5#prob-title

(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))

;; Intro to vectors
;; http://www.4clojure.com/problem/6#prob-title

(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))
(= 1 (- 2 1) (* 1 1))

;; Vector - Conj
;; http://www.4clojure.com/problem/7#prob-title

(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))
(= '(1 2 3 4) (conj [1 2] 3 4))

;; Sets
;; http://www.4clojure.com/problem/8#prob-title

(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

;; Sets - conj
;; http://www.4clojure.com/problem/9#prob-title

(= #{1 2 3 4} (conj #{1 4 3} 2))
(= #{1 4 2 3} #{1 2 3 4})

;; Maps
;; http://www.4clojure.com/problem/10

(= 20 ((hash-map :a 10, :b 20, :c 30) :b))
(= 20 (:b {:a 10, :b 20, :c 30}))

;;; Hash map as a function of key or key as a function of hashmap works
({:a 20 :b 30} :b)
(:b {:a 20 :b 30})

;; maps - conj
;; http://www.4clojure.com/problem/11#prob-title

(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))

;;; Can conj a vector or a map to an existing map
(conj {:a 1} {:b 2})
(conj {:a 1} [:b 2] [:c 3])

;; Seq
;; http://www.4clojure.com/problem/12#prob-title

(= 3 (first '(3 2 1)))
(= 3 (second [2 3 4]))
(= 3 (last (list 1 2 3)))

;; Seq - rest
;; http://www.4clojure.com/problem/13#prob-title

(= [20 30 40] (rest [10 20 30 40]))

;; Fxn
;; http://www.4clojure.com/problem/14#prob-title

(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

;; double down
;; http://www.4clojure.com/problem/15#prob-title

(= (#(* % 2) 2) 4)
(= (#(* % 2) 3) 6)
(= (#(* % 2) 11) 22)
(= (#(* % 2) 7) 14)

;; hello world
;; http://www.4clojure.com/problem/16#prob-title

(= ((fn [x] (str "Hello, " x "!")) "Dave") "Hello, Dave!")
(= ((fn [x] (str "Hello, " x "!")) "Jenn") "Hello, Jenn!")
(= ((fn [x] (str "Hello, " x "!")) "Rhea") "Hello, Rhea!")

;; Sequences - map
;; http://www.4clojure.com/problem/17#prob-title

(= [6 7 8] (map #(+ % 5) '(1 2 3)))

;; Sequences - Filter
;; http://www.4clojure.com/problem/18#prob-title

(= [6 7] (filter #(> % 5) '(3 4 5 6 7)))

;; last element
;; http://www.4clojure.com/problem/19#prob-title

(= (__ [1 2 3 4 5]) 5)
(= (__ '(5 4 3)) 3)
(= (__ ["b" "c" "d"]) "d")

;;; Apply below fxn to __ above
((fn my-last
  [list]
  (let [[first & rest] list]
    (if (empty? rest)
      first
      (my-last rest)))) [1 2 3])

;;; Other solutions

(first (reverse [1 2 3]))
(comp [1 2 3])
((comp first) [1 2 3])

;; Penultimate element
;; http://www.4clojure.com/problem/20#prob-title

(= (__ (list 1 2 3 4 5)) 4)
(= (__ ["a" "b" "c"]) "b")
(= (__ [[1 2] [3 4]]) [1 2])

(first (rest (reverse [1 2 3])))
(#(first (rest (reverse %))) [1 2 3 4 5])

;; Nth element
;; http://www.4clojure.com/problem/21#prob-title

((fn [list
     n]
  (loop [curr 1
         remaining list]
    (let [[curr-elem & rest] remaining]
      (if (not (= curr n))
        (recur (inc curr) rest)
        curr-elem)))) [1 2 3] 2)
    
(fn [list
     n]
  (loop [curr 0
         remaining list]
    (let [curr-elem (first remaining)
          rem-elems (rest remaining)]
      (if (not (= curr n))
        (recur (inc curr) rem-elems)
        curr-elem))))
