(ns clojurewebapplication.domain.meal
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]))

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
