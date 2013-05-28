(use 'tictactoe_server.game)

; sample data

(do 
(def blanks [:blank :blank :blank])
(def xs [:x :x :x])
(def blank-middle [:x :blank :x]))

[xs blank-middle blanks]



(def state (game-state))

(reduce (fn [results [row column-values]]
          ) 
        []
        state)

; Finds the column indices that are blank
(reduce (fn [results [column v]]
          (if (= v :blank)
            (conj results column)
            results))
        []
        {1 :blank, 2 :blank, 3 :blank})

; Does the same thing but a little better
(map first 
     (filter (fn [[_ v]] (= v :blank)) 
             {1 :blank, 2 :blank, 3 :blank}))

; Another shorter variation
(map first 
     (filter #(= (last %) :blank)
             {1 :blank, 2 :blank, 3 :blank}))

; Another shorter variation
(map first 
     (filter #(= (last %) :blank)
             {1 :blank, 2 :blank, 3 :blank}))

; Handles a sequence
(defn find-blank-columns [s]
  (map first 
       (filter #(= (last %) :blank)
               (map-indexed vector s))))


(def rows [[:blank :blank :blank]
   [:blank :blank :blank]
   [:blank :blank :blank]])

(map-indexed 
  (fn [ind columns] [ind (find-blank-columns columns)]) 
   rows)

; todo
(defn find-rows-with-blanks [s] s)
     
     