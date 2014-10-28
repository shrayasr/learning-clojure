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
