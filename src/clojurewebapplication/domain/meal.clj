(ns clojurewebapplication.domain.meal
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def mysql-db {
               :subprotocol "mysql"
               :subname "//localhost:3306/clojure_database"
               :user "root"
               :password ""
               :zeroDateTimeBehaviour "convertToNull"
               })

(defn allMeals []
  (jdbc/query mysql-db
               ["SELECT * FROM meal m"]))

(defn get [id]
  (first (jdbc/query mysql-db
                     ["SELECT * FROM meal WHERE meal_id = ?" id])))

(defn delete [id]
  (jdbc/execute! mysql-db
                 ["DELETE FROM meal WHERE meal_id = ?" id]))

(defn update [id params]
  (jdbc/update! mysql-db :meal params (sql/where {:meal_id id})))
