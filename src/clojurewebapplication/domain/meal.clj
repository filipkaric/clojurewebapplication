(ns clojurewebapplication.domain.meal
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]
            [struct.core :as st]))

(def mysql-db {
               :subprotocol "mysql"
               :subname "//localhost:3306/clojure_database"
               :user "root"
               :password ""
               :zeroDateTimeBehaviour "convertToNull"
               })

(def schemeUpdate
  {:meal_id [st/required st/number-str]
   :meal_name [st/required st/string [st/max-count 60]]
   :kcal_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :proteins_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :carbs_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :fats_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :meal_type [st/required st/number-str st/positive [st/in-range 0 10000]]
   })

(def schemeInsert
  {:meal_name [st/required st/string [st/max-count 60]]
   :kcal_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :proteins_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :carbs_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :fats_100g [st/required st/number-str st/positive [st/in-range 0 10000]]
   :meal_type [st/required st/number-str st/positive [st/in-range 0 10000]]
   })

(defn allMeals []
  (jdbc/query mysql-db
               ["SELECT * FROM meal m"]))

(defn allMealTypes []
  (jdbc/query mysql-db
              ["SELECT * FROM meal_type mt"]))

(defn get [id]
  (first (jdbc/query mysql-db
                     ["SELECT * FROM meal WHERE meal_id = ?" id])))

(defn delete [id]
  (jdbc/execute! mysql-db
                 ["DELETE FROM meal WHERE meal_id = ?" id]))

(defn update [id params]
  (if (st/valid? params schemeUpdate) (jdbc/update! mysql-db :meal params (sql/where {:meal_id id})) (throw (Exception. "Validation failed")))
  )

(defn insertMeal [params]
  (if (st/valid? params schemeInsert) (jdbc/insert! mysql-db :meal params) (throw (Exception. "Validation failed")))
  )
