(ns clojurewebapplication.domain.dailymenu
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

(defn allMenus []
  (jdbc/query mysql-db
              ["SELECT * FROM daily_menu d"]))

(defn allMenuTypes []
  (jdbc/query mysql-db
              ["SELECT * FROM daily_menu_type dmt"]))

(defn allChefs []
  (jdbc/query mysql-db
              ["SELECT * FROM chef c"]))

(defn getMenu [id]
  (first (jdbc/query mysql-db
                     ["SELECT * FROM daily_menu WHERE menu_id = ?" id])))

(defn deleteMenu [id]
  (jdbc/execute! mysql-db
                 ["DELETE FROM daily_menu WHERE menu_id = ?" id]))

(defn updateMenu [id params]
  (jdbc/update! mysql-db :menu params (sql/where {:menu_id id})))

(defn insertMenu
  [params]
  (jdbc/insert! mysql-db :menu params))
