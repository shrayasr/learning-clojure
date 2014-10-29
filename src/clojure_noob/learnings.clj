;;;;;;;;;;;;;;;;;;;;;;;;
;; Control Structures ;;
;;;;;;;;;;;;;;;;;;;;;;;;

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

;;;;;;;;;;;;;;;;;;;;;
;; Data Structures ;;
;;;;;;;;;;;;;;;;;;;;;

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

;;;;;;;;;;;;;;;
;; Functions ;;
;;;;;;;;;;;;;;;

(defn do-op
  [coolness-level]
  (if (= coolness-level "UBER")
    +
    -))

((do-op "ASDF") 1 2)

;; Fxn taking fxn as argument
(inc 1)
(dec 1)

(map inc [1 2 3])

;; Fxn evaluation
(+ (dec 5) (/ (* 1 (- 10 5)) (- 10 5)))

;; Evaluation order:
;; (+ 4 (/ (* 1 (- 10 5)) (- 10 5))) ; Evaluate dec
;; (+ 4 (/ (* 1 (- 10 5)) 5)) ; Evaluate (- 10 5)
;; (+ 4 (/ (* 1 5) 5)) ; Evaluate (- 10 5)
;; (+ 4 (/ 5 5)) ; Evaluate (* 1 5)
;; (+ 4 1) ; Evaluate (/ 5 5)
;; 5 ; Evaluate (+ 4 1)

;; Defining functions
(defn this-is-a-function
  "I'm going to document this function like there is no tomorrow"
  [arg1
   arg2]
  (+ arg1 arg2))

(this-is-a-function 1 2)


(defn multi-arity-fxn
  ([one two three]
     (println (str one two three)))
  ([one two]
     (println (str one two)))
  ([one]
     (println (str one))))

(multi-arity-fxn 1)
(multi-arity-fxn 1 2)
(multi-arity-fxn 1 2 3)

;;; Multi arity is the way to do default arguments in clj
(defn make-chocolate
  ([name
    flavour]
     {:name name :flavour flavour})
  ([name]
     (make-chocolate name "chocolate")))

(make-chocolate "hersheys" "milk")
(make-chocolate "snickers")

;;; Specifying an arg with & before it makes clj put all the args within a list
;;; and return it
(defn var-arity
  [& args]
  (map inc args))

(var-arity 1 2 3 4 5)

(defn say-hi
  [& people]
  (defn _say-hi
    [person]
    (println (str "Hey " person ", how're u?")))
  (map _say-hi people))

(say-hi "shrayas")
(say-hi "Shrayas" "Gugu" "Nishi")

(defn fav-things
  [name
   & things]
  (println (str "I'm " name " and I like these things: "
                (clojure.string/join ", " things))))

(fav-things "Shrayas" "Computers" "Photography")

;; Destructuring
;;; Neat concept where clj allows you to name an incoming set or vector elements
;;; with particular names based on location
(defn make-person
  [[name age sex]]
  {
   :name name
   :age  age
   :sex  sex
   })

(make-person ["Shrayas" 25 :male])
(make-person ["Shrayas" 25 :male :more-args])

(defn make-person
  [[
    name
    age
    sex
    & others]]
  {
   :name name
   :age age
   :sex sex
   :others others
   })

(make-person ["Shrayas" 25 :male "Software Engineer" "Foodie"])

(defn make-person
  [{name :name sex :sex}]
  (if (= (first name) \S)
    "AWESOME!"
    "BOO"))

(make-person {:name "Shrayas"})

(defn make-person
  [{:keys [name sex]}]
  (println (str name " " sex)))

(make-person {:name "shrayas" :sex :male})


(defn make-person
  [{:keys [name sex] :as person}]
  (println person)
  (println (str name " " sex)))

(make-person {:name "shrayas" :sex :male :more "things here"})

;; Anon functions, yo! Lambdas, yo!
(map (fn [a b]
       (+ a b))
     [1 2]
     [3 4])

;;; Can use this form to create lambdas. The % is used to indicate arguments.
;;; % defaults to the first arg, to access more use %1 %2, etc
(map #(* %1 %2) [1 2] [3 4])

;; Returning functions
(defn dec-maker
  [dec-by]
  #(- % dec-by))

(def dec3 (dec-maker 3))
(dec3 10)

;;;;;;;;;;;;;
;; THE END ;;
;;;;;;;;;;;;;
