(ns myonboard.routes
  (:use [compojure.route :only [files not-found]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server)
  (:require [myonboard.dbConnection :as dbc])
  )


;dont understand the web-socket ,async,files.

(defn perform-insert [req]
  (let [{:keys [subscriberid]} (:param req)]
    (dbc/insert-user-data subscriberid)
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    "hello HTTP2!"}))






