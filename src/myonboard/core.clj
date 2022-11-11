(ns myonboard.core
  (:gen-class)
  (:require [clojure.java.jdbc :as sql]
            [myonboard.dbConnection :as dbc]
            [compojure.handler :as handler]
            [myonboard.routes :as routes]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]])
  (:use [compojure.route :only [files not-found]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server))

;(database-connection-string "postgresql://postgres:postgres@localhost:5432/onboard")

;The app function what request does it need?
;(defn app [req]
;  (println req)
;  {:status  200
;   :headers {"Content-Type" "text/html"}
;   :body    "hello HTTP!"})

(defroutes all-routes
           ;(GET "/core" request (routes/perform-insert request))
           (GET "/val" request "Helloooo")
           ;(context "/subscriber/:id" []
           ;  (GET / [] get-sub-by-id)
           ;  (POST / [] update-sub-info))
           )

(defn app2 [req]
  ;(-> handler/api all-routes
  ;    (wrap-json-response )
  ;    (wrap-json-body {:keyword? true})
  ;    )

  (let [{:keys [subscriberid]} (:param req)]
    (dbc/insert-user-data subscriberid)
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    "Insert subscriber number"})
  )

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  ;(println "Hello, World!")
;(update-subs)
  (reset! server (run-server #'app2 {:port 8180}))
  ;(dbc/insert-user-data subscriberid)
  )

;(-> #'app2 (wrap-reload))


