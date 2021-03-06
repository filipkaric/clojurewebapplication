(ns clojurewebapplication.domain.dailymenu
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]
            [struct.core :as st]))

(def schemeUpdate
  {:menu_id       [st/required st/number-str]
   :name     [st/required st/string [st/max-count 60]]
   :date_created     [st/required]
   :type     [st/required st/number-str st/positive]
   :chef     [st/required st/number-str st/positive]
   })

(def schemeInsert
  {:name     [st/required st/string [st/max-count 60]]
   :date_created     [st/required]
   :type     [st/required st/number-str st/positive]
   :chef     [st/required st/number-str st/positive]
   })

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
  (jdbc/db-do-commands mysql-db
                       (jdbc/execute! mysql-db ["DELETE FROM daily_menu WHERE menu_id = ?" id])))

(defn updateMenu [id params]
  (if (st/valid? params schemeUpdate) (jdbc/db-do-commands mysql-db (jdbc/update! mysql-db :daily_menu params (sql/where {:menu_id id})))
                                      (throw (Exception. "Validation failed"))))


(defn insertMenu
  [params]
  (if (st/valid? params schemeInsert) (jdbc/db-do-commands mysql-db  (jdbc/insert! mysql-db :daily_menu params))
                                      (throw (Exception. "Validation failed"))))
