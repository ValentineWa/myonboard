(ns myonboard.dbConnection
  (:require [clojure.java.jdbc :as sql]))

(def database-connection-string "postgresql://postgres:postgres@localhost:5432/onboard")

