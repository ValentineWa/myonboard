(ns myonboard.dbConnection
  (:require [clojure.java.jdbc :as sql]))

(def database-connection-string "postgresql://postgres:postgres@localhost:5432/onboard")


(defn create-subscribers
  "Create a user table to store usernames"
  []
  (sql/db-do-commands database-connection-string
                      (sql/create-table-ddl :subscribers [[:id :serial "PRIMARY KEY"]
                                                          [:subscriberid :integer]
                                                          [:phonenumber :integer]])))

(defn insert-multi-subs
  "Insert multiple row data"
  []
  (sql/insert-multi! database-connection-string
                     ::subscribers [{:subscriberid 1001}
                                    {:subscriberid 2002}
                                    ]
                     ))

(defn insert-user-data
  "Insert single row data"
  [subscriberid phonenumber]
  (sql/insert! database-connection-string
               :subscribers {:subscriberid subscriberid}
               ))


(defn insert-multi2-user-data
  "Insert multiple row data"
  []
  (sql/db-do-prepared database-connection-string
                      ["INSERT INTO subscribers (subscriberid, phonenumber) VALUES (?, ?)"
                       [1001 07123]
                       [2002 07876]] {:multi? true}))

(defn update-subs
  "Update a record"
  [id]
  (sql/update! database-connection-string
               :subscribers {:subscriberid 3033} ["subscriberid = ?" 3003]
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