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

;;; Other solutions. Drop is nice
(#(first (drop %2 %1)) [1 2 3] 2)

;; Count a sequence
;; http://www.4clojure.com/problem/22

(= (__ '(1 2 3 3 1)) 5)

(reduce (fn [initial final]
  (println (str initial final))) [1 2 3])

(reduce (fn [x y]
          (println (str x y))) [1 2 3])

(reduce + 1 [1 2 3])
(reduce #(+ %1 %2) 1 [1 2 3])

((fn [x]
  (loop [list-to-iterate x
         curr-count 0]
    (if (empty? list-to-iterate)
      curr-count
      (recur (rest list-to-iterate) (inc curr-count))))) '(1 2 3))

;; Reverse a sequence
;; http://www.4clojure.com/problem/23

(reduce #(conj (lazy-seq %1) %2) '() [1 2 3])

;; Sum it all up
;; http://www.4clojure.com/problem/24#prob-title

(reduce + 0 [1 2 3])

;; Find odd nos
;; http://www.4clojure.com/problem/25#prob-title

(= (__ [4 2 1 6]) '(1))

(filter #(= (mod %1 2) 0) [1 2 3 4 5])

;; Fib sequence
;; http://www.4clojure.com/problem/26#prob-title

(= (__ 3) '(1 1 2))

(= ((fn [limit]
  (loop [first 1
         second 1
         count 2
         current-list (conj [] first second)]
    (if (= count limit)
      current-list
      (let [third (+ first second)
            current-list (conj current-list third)]
        (recur second third (inc count) current-list))))) 3) '(1 1 2))

((fn [x]
  (if (= x 3)
    x
    (do
      (println x)
      (recur (inc x))))) 0)

;; Palindrome Detector
;; http://www.4clojure.com/problem/27#prob-title

(#(= % (reverse %)) "level")

(str (reverse "level"))

(clojure.string/join "" [\a \b \c])

(clojure.string/join "" (reverse "level"))

(#(= % (clojure.string/join "" (reverse %))) "level")

(defn is-palindrome? [input]
  (loop [seq-to-check input
         is-palindrome 't]
    (if (empty? seq-to-check)
      is-palindrome
      (let [first-elem (first seq-to-check)
            last-elem (last seq-to-check)
            remaining-elems (drop-last (drop 1 seq-to-check))]
        (if (= first-elem last-elem)
          (recur remaining-elems 't)
          'f)))))

(is-palindrome? [1 2 3])
(is-palindrome? [1 2 1])
(is-palindrome? [:foo :bar :foo])


(false? ((fn [input]
  (loop [seq-to-check input
         is-palindrome 't]
    (if (empty? seq-to-check)
      is-palindrome
      (let [first-elem (first seq-to-check)
            last-elem (last seq-to-check)
            remaining-elems (drop-last (drop 1 seq-to-check))]
        (if (= first-elem last-elem)
          (recur remaining-elems 't)
          'f))))) '(1 2 3 4 5)))


((fn [input]
  (loop [seq-to-check input
         is-palindrome 't]
    (if (empty? seq-to-check)
      is-palindrome
      (let [first-elem (first seq-to-check)
            last-elem (last seq-to-check)
            remaining-elems (drop-last (drop 1 seq-to-check))]
        (if (= first-elem last-elem)
          (recur remaining-elems 'true)
          'f))))) '(1 2 3 4 5))

;; Flatten a sequence
;; http://www.4clojure.com/problem/28

(fn [input]
  (loop [list-to-flatten input
         flattened-list []]
    (let [[elem & remaining] list-to-flatten]
      (if (empty? remaining)
        flattened-list
        (if (instance? clojure.lang.Sequential remaining))))))

(= ((fn [input]
  (loop [list-to-flatten input
         flattened-list []]
    (if (empty? list-to-flatten)
      flattened-list
      (let [[item & rem] list-to-flatten
            is-seq? (#(instance? clojure.lang.Sequential %) item)]
        (if is-seq?
          (recur (concat item rem) flattened-list)
          (recur rem (conj flattened-list item))))))) '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))

;; Get the caps
;; http://www.4clojure.com/problem/29

(defn is-uppercase? [char-to-inspect]
  (if (and (>= (int char-to-inspect) 65) (<= (int char-to-inspect) 90))
    char-to-inspect))

(is-uppercase? \A)

(map is-uppercase? "AsdF")

(reduce str "" (map is-uppercase? "AsdF"))

(= (__ "HeLlO, WoRlD!") "HLOWRD")

((fn [input]
  (reduce str "" (map is-uppercase? input))) "HeLlO, WoRlD!")

(apply str (map is-uppercase? "heLLO WORld"))

(apply str (map #(when(Character/isUpperCase %) %) "HeLLO"))

;; Compress sequence
;; http://www.4clojure.com/problem/30

(defn some-fxn
  [input]
  (loop [seq-to-iterate input
         already-visited #{}
         compressed-seq []]
    (if (empty? seq-to-iterate)
      compressed-seq
      (let [[curr-char & rest-chars] seq-to-iterate
            is-already-in-map? (already-visited curr-char)]
        (if is-already-in-map?
            (recur rest-chars already-visited compressed-seq)
            (recur rest-chars (conj already-visited curr-char) (conj compressed-seq curr-char)))))))

(fn [input]
    (loop [seq-to-iterate input
         last-item nil
         compressed-seq []]
    (if (empty? seq-to-iterate)
      compressed-seq
      (let [[curr-item & remaining-items] seq-to-iterate
            is-last-item? (= last-item curr-item)]
        (if is-last-item?
          (recur remaining-items curr-item compressed-seq)
          (recur remaining-items curr-item (conj compressed-seq curr-item)))))))


(compress-conseq-items "Leeeeroy")
(compress-conseq-items [1 1 2 3 3 2 2 3])

;; Pack a Sequence
;; http://www.4clojure.com/problem/31

(partition-by list [1 1 2 1 1 1 3 3])

;; duplicate a sequence
;; http://www.4clojure.com/problem/32#prob-title

(apply concat (map (fn [input]
       [input input]) [1 2 3]))

(= ((fn [input]
      (apply concat (map #(do [% %]) input))) [1 2 3]) '(1 1 2 2 3 3))

;; Replicate a sequence
;; http://www.4clojure.com/problem/33#prob-title

(fn [input
     times]
  (apply interleave (take times (repeat input))))

;; for 4clojure
(fn [input
     times]
  (if (= times 1)
    input
    (apply interleave (take times (repeat input)))))

