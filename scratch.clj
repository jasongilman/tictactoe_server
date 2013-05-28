(use 'tictactoe_server.game)

; sample data

(do 
(def blanks [:b :b :b])
(def xs [:x :x :x])
(def blank-middle [:x :b :x])

(def row-winner [blank-middle xs blank-middle])
(def col-winner [[:o :b :x] blank-middle blank-middle])

(def stale-mate [[:o :b :x]
                 [:o :b :x]
                 [:x :o :o]])

(def diag-winner1 [[:o :x :x]
                   [:x :o :x]
                   [:x :x :o]])

(def diag-winner2 [[:x :x :o]
                   [:x :o :x]
                   [:o :x :x]])
)


(defn winner [rows]
  (let [col-vals (map (fn [col-index] 
                        (map (fn [row] (row col-index)) rows))
                      (range 3))
        diagonal1 (map #(get-in rows %) [[0 0] [1 1] [2 2]])
        diagonal2 (map #(get-in rows %) [[0 2] [1 1] [2 0]])
        diagonals [diagonal1 diagonal2]
        sets (-> rows (concat col-vals) (concat diagonals))
        winners (map distinct sets)]
    (first (filter 
             #(not= % [:b]) 
             (filter 
               #(= 1 (count %)) 
               winners)))))


(map (fn [row] (row 2)) col-winner)

(def state (game-state))

(reduce (fn [results [row column-values]]
          ) 
        []
        state)

; Finds the column indices that are blank
(reduce (fn [results [column v]]
          (if (= v :b)
            (conj results column)
            results))
        []
        {1 :b, 2 :b, 3 :b})

; Does the same thing but a little better
(map first 
     (filter (fn [[_ v]] (= v :b)) 
             {1 :b, 2 :b, 3 :b}))

; Another shorter variation
(map first 
     (filter #(= (last %) :b)
             {1 :b, 2 :b, 3 :b}))

; Another shorter variation
(map first 
     (filter #(= (last %) :b)
             {1 :b, 2 :b, 3 :b}))

; Handles a sequence
(defn find-blank-columns [s]
  (map first 
       (filter #(= (last %) :b)
               (map-indexed vector s))))


(def rows [[:b :b :b]
   [:b :b :b]
   [:b :b :b]])

(map-indexed 
  (fn [ind columns] [ind (find-blank-columns columns)]) 
   rows)

; todo
(defn find-rows-with-blanks [s] s)
     
     