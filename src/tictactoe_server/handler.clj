(ns tictactoe_server.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [tictactoe_server.game :as game]
            [tictactoe_server.player :as player]
            [ring.middleware.edn :as edn]))

(defn generate-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defn get-game [id]
  (let [g (game/get-game (read-string id))]
    (if g
      (generate-response g)
      (generate-response (str "No game with id " id) 404))))
  
; TODO not quite happy with how this one is laid out. An improvement might be to 
; do some sort of chaining here like interceptors. 
(defn mark [id data]
  (let [g (game/get-game (read-string id))]
    (cond
      
      (not g) 
      (generate-response (str "No game with id " id) 404)
      
      (not (and (map? data)
                (= (set (keys data)) #{:row :column}))) 
      (generate-response 
        "Mark must be passed a clojure map with row and column" 
        400)
      
      :else 
      (let [row (:row data)
            column (:column data)
            errors (game/validate-update g :x row column)]
        (if (> (count errors) 0)
          (generate-response errors 400)
          (let [game2 (game/mark-position g :x row column)]
            (if (:winner game2)
              (generate-response game2)
              (generate-response (player/make-move game2)))))))))
      
(defroutes app-routes
  (GET "/" [] "Try posting to /games")
  (context 
    "/games" [] 
    (defroutes games-routes
      (GET  "/" [] (generate-response (game/get-games)))
      (POST "/" [] (generate-response (game/create-game) 201))
      (context 
        "/:id" [id]
        (defroutes game
          ; TODO can use middleware here to check for existance of game
          ; and put it in the request
          (GET  "/" [] (get-game id))
          (POST "/mark" {edn-params :edn-params} (mark id edn-params))))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      edn/wrap-edn-params))
