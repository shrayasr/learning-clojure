(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "body" :size 10}
                             {:name "left-hand" :size 4}
                             {:name "left-leg" :size 4}])
(defn needs-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn make-matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a sequence of body parts with a :name and a :size"
  [asym-body-parts]
  (loop [remaining-body-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-body-parts)
      final-body-parts
      (let [[part & remaining] remaining-body-parts
            final-body-parts (conj final-body-parts part)]
        (if (needs-matching-part? part)
          (recur remaining (conj final-body-parts (make-matching-part part)))
          (recur remaining final-body-parts))))))

(defn better-symmetrize-body-parts
  "Expects a sequence of body parts with a :name and a :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (let [final-body-parts (conj final-body-parts part)]
              (if (needs-matching-part? part)
                (conj final-body-parts (make-matching-part part))
                final-body-parts)))
          []
          asym-body-parts))

(symmetrize-body-parts asym-hobbit-body-parts)
(better-symmetrize-body-parts asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-body-parts (better-symmetrize-body-parts asym-hobbit-body-parts)
        body-part-size-sum (reduce + 0 (map :size sym-body-parts))
        target (inc (rand body-part-size-sum))]
    (loop [[part & rest] sym-body-parts
           accum-size (:size part)]
      (if (> accum-size target)
        part
        (recur rest (+ accum-size (:size part)))))))

(hit asym-hobbit-body-parts)

