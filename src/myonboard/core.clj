(ns myonboard.core
  (:gen-class)
  (:require [clojure.java.jdbc :as sql]))

(def database-connection-string "postgresql://postgres:postgres@localhost:5432/onboard")

(defn create-subscribers
  "Create a user table to store usernames"
  []
  (sql/db-do-commands database-connection-string
                      (sql/create-table-ddl :subscribers [[:id :serial :primary]
                                                    [:subscriber :primary]])))


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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (create-subscribers)
  )
