(ns tictactoe_server.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [tictactoe_server.game :as game]
            [ring.middleware.edn :as edn]))

(defn generate-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/games" [] (generate-response (game/get-games)))
  (POST "/games" [] (generate-response (game/create-game) 201))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      edn/wrap-edn-params))
