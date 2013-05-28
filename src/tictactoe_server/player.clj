(ns tictactoe_server.player
  (:require [tictactoe_server.game :as model]))

(defn- find-blank-columns 
  "Finds indices of columns with a blank value in the row."
  [row]
  (map first 
       (filter #(= (last %) :b)
               (map-indexed vector row))))

(defn- find-all-blank-indices 
  "Returns a sequence of maps for each row containing the row index and the 
  blank columns for that row."
  [game]
  (filter #(> (->> % (:blank-cols) (count)) 0)
          (map-indexed 
            (fn [ind columns] {:row-index ind :blank-cols (find-blank-columns columns)}) 
            (:game-state game))))

(defn- find-first-blank-row-column 
  "Returns the indices of the first blank row and column"
  [game]
  (let [blank-indices (find-all-blank-indices game)
        first-row (first blank-indices)]
    [(:row-index first-row) (first (:blank-cols first-row))]))

(defn make-move-in-first-open-position 
  "Makes the next move in the first open position"
  [game] 
  (let [[row column] (find-first-blank-row-column game)]
    (model/mark-position game :o row column)))

; Alias make-move to make-move-in-first-open-position
(def make-move make-move-in-first-open-position)