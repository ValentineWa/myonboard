(ns myonboard.routes
  (:use [compojure.route :only [files not-found]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server)
  (:require [myonboard.core :as onboard]))

(defn update-sub-info [req]          ;; ordinary clojure function
  (let [id (-> req :params :id)    ; param from uri
        subscriberid (-> req :params :subscriberid)] ; form param
    ))

;dont understand the web-socket ,async,files.
(defroutes all-routes
           (GET "/core" [] create-subscribers)
           (GET "/ws" [] chat-handler)     ;; websocket
           (GET "/async" [] async-handler) ;; asynchronous(long polling)
           (context "/subscriber/:id" []
             (GET / [] get-sub-by-id)
             (POST / [] update-sub-info))
           (files "/static/") ;; static file url prefix /static, in `public` folder
           (not-found "<p>Page not found.</p>")) ;; all other, return 404

(run-server all-routes {:port 8080})




