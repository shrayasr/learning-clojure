;; if
(if true
  (println "True")
  (println "False"))

;; if + do
(if true
  (do
    (println str (+ 1 1)))
  (do
    (println str (* 2 2))))

;; when
(when true
  (println "Executed when true"))

;; def
(def something 1)
(println something)

;; severity - wrong
;; => This is wrong because it uses error-msg mutabily which isn't the way FP works really.

(def severity :mild)
(def error-msg "Error! ")
(if (= severity :mild)
  (def error-msg (str error-msg "sooome problem"))
  (def error-msg (str error-msg "OMFG!")))



;; severity - right
;; => This is right because things are immutable here.
(defn get-error-msg
  [severity]
  (def error-msg "Error! ")
  (if (= severity :mild)
    (str error-msg "sooome prob")
    (str error-msg "OMFG!")))

;; nil
(def x nil)
(nil? x)

;; Equality
(= 1 1)

;; Numberz
1
1/2
1.2

;; Strings
"Hello, Clj"
"\"poda\", she said"

;; Maps
{}

;;; defines a map
(def person {:name      "Shrayas"
             :age       25
             :sex       :male
             :interests []})

;;; Access something from the map
(get person :name)

;;; define a nested map
(def nested-person {
                    :name {
                           :first "Shrayas"
                           :last  "Rajagopal"}})

;;; Access of an access, nested gets?
(get (get nested-person :name) :first)
;;; get-in can also be used
(get-in nested-person [:name :first])

;;; Try to get something, returns nil if it doesn't exist
(get person :marks)

;;; Try to get something, return a default val if it doesn't exist
(get person :marks "LOL")

;;; Treat the map as a function, ask for its key
(person :name)
((nested-person :name) :first)

;; Keywords
:a

(def person {:name "Shrayas"
             :age   25})

(def nested-person {:name {:first "Shrayas"
                           :last  "Rajagopal"}
                    :age  25})

(:name person)
(:first (:name nested-person))

;; Vectors
[1 2 3]

(def some-vector [1 "a" {:name "Shrayas"} 0.1])
(get some-vector 1)

;;; Conj adds to end of vector
(conj some-vector 1 2 3)
(conj some-vector {:age 12})

;; List
'(1 2 3)

;;; Doesn't workS
(get '(1 2 3) 0)

;;; Conj adds to beginning of list
(conj '(1 2 3) 0)

;; Sets
#{1 2 3}

;;; If something already exists, can't add to it. 
(conj #{1 2 3} 3)
(conj #{1 2 3} 4)

(nil? (get #{1 2 3} 4))

(#{1 2 3} 3)

;;; Use case: Take a vector and check if an element exists in it
(def vector-to-check [1 2 3 4 5])

(defn elem-in-vector?
  [vector
   element]
  (not (nil? ((set vector) element))))

(elem-in-vector? [1 2 3 4] 4)

;; Symbols????
(identity 1)

;; Quoting
(def some-vector [1 2 3])
some-vector
'some-vector
(eval 'some-vector)

(first (eval 'some-vector))
