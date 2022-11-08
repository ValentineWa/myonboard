(ns myonboard.core
  (:gen-class)
  (:require [clojure.java.jdbc :as sql])
  (:use org.httpkit.server)
  )

(def database-connection-string "postgresql://postgres:postgres@localhost:5432/onboard")

(defn create-subscribers
  "Create a user table to store usernames"
  []
  (sql/db-do-commands database-connection-string
                      (sql/create-table-ddl :subscribers [[:id :serial "PRIMARY KEY"]
                                                    [:subscriberid :integer]
                                                          [:phonenumber :integer]])))
(defn insert-user-data
  "Insert single row data"
  []
  (sql/insert! database-connection-string
               :users {:username "peter"}
               ))

(defn insert-multi-subs
  "Insert multiple row data"
  []
  (sql/insert-multi! database-connection-string
                     ::subscribers [{:subscriberid 1001 }
                             {:subscriberid 2002}
                           ]
                     ))

;(defn insert-multi2-user-data
;  "Insert multiple row data"
;  []
;  (sql/db-do-prepared database-connection-string
;                      ["INSERT INTO subscribers (subscriberid, phonenumber) VALUES (?, ?)"
;                       [1001 07123]
;                       [2002 07876]] {:multi? true}))

(defn update-subs
  "Update a record"
  []
  (sql/update! database-connection-string
               :subscribers {:subscriberid 1001} ["subscriberid = ?" 3003]
               ))


(defn create-loans
  "Create a user table to store usernames"
  []
  (sql/db-do-commands database-connection-string
                      (sql/create-table-ddl :loans [[:id :serial "PRIMARY KEY"]
                                                    ])))


(defn create-transactions
  "Create a user table to store usernames"
  []
  (sql/db-do-commands database-connection-string
                      (sql/create-table-ddl :transactions [[:id :serial "PRIMARY KEY"]
                                                    ])))

;The app function what request does it need?
(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello HTTP!"})

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  ;(println "Hello, World!")
(update-subs)
  ;(reset! server (run-server #'app {:port 8080}))
  )




