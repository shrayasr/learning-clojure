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
