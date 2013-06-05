(ns tictactoe_server.t-game
  (:use midje.sweet)
  (:require [tictactoe_server.game :as game]))

(with-state-changes [(before :facts (game/delete-all))]
  (facts "about `create-game`"
    (fact "game-state is blank"
      (:game-state (game/create-game)) => [[:b :b :b] 
                                           [:b :b :b] 
                                           [:b :b :b]],)
    (fact "created game can be retrieved"
      (do 
        (game/create-game)
        (first (game/get-games))) => {:game-state [[:b :b :b] 
                                                   [:b :b :b] 
                                                   [:b :b :b]], 
                                      :id 0})))
