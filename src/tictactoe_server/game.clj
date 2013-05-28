(ns tictactoe_server.game)

; In memory database of the entire set of games. 
(def ^:private games (ref {} ) )

(defn- create-game-state 
  "Creates a new empty game state"
  [] 
  [[:blank :blank :blank]
   [:blank :blank :blank]
   [:blank :blank :blank]])

(defn create-game
  "Creates a new game and returns it's id"
  []
  (dosync 
    (let [game-id (count @games)
          new-game {:id game-id :game-state (create-game-state)}]
      (alter games assoc game-id new-game)
      new-game)))

(defn- update-game 
  "Updates the game in the games reference."
  [game]
  (dosync
    (alter games assoc (:id game) game)))

(defn mark-position 
  "Places an X or O in the game at the given column and then saves
  The game."
  [game x-or-o row column]
  (let [new-game (assoc-in game [:game-state row column] x-or-o)]
    (update-game new-game)
    new-game))

(defn get-games
  "Returns all the current games"
  []
  (vals @games))

(defn get-game
  "Gets a game by id or nil if the game doesn't exist"
  [id]
  (@games id))

; TODO figure out a better way to do validations

(defn- validate-row [game x-or-o row column]
  (if (or (> row 2) (< row 0)) 
    ["Row should be a value 0, 1, or 2."]
    []))

(defn- validate-column [game x-or-o row column]
  (if (or (> column 2) (< column 0)) 
    ["Column should be a value 0, 1, 2"]
    []))

(defn- validate-x-or-o [game x-or-o row column]
  (if (and (not= :o x-or-o) (not= :x x-or-o))
    ["x-or-o should be :x or :o"]
    []))

(defn- validate-update-location [game x-or-o row column]
  (let [current-mark (get-in game [:game-state row column])]
    (if (not= :blank current-mark)
      [(str "The current mark at row:" row " and column:" column " is " current-mark)]
      [])))
  
(defn validate-update
  "Validates that a new value in the game state is allowed with 
  the current value. Returns any errors"
  [game x-or-o row column]
  ; TODO I don't really like the way that I wrote this but I'm not super 
  ; familiar with idiomatic clojure to think of a better way
  (let [validations [validate-row
                     validate-column 
                     validate-x-or-o
                     validate-update-location]]
    (reduce (fn [errors f] 
              (concat errors (f game x-or-o row column)))
            [] 
            validations)))