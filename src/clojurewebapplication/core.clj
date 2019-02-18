(ns clojurewebapplication.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
            [ring.util.response :as resp]
            [clojurewebapplication.domain.meal :as meal-domain]
            [clojurewebapplication.controller.controller :as controller]
            )
  )

(defroutes public-routes
           (GET "/" [] (controller/home))
           (route/resources "/")
           (GET "/homepage" [] (controller/home))
           (route/resources "/")
           (GET "/allMeals" [] (controller/allMeals))
           (route/resources "/")

           (GET "/domain/meals/:id/delete" [id]
             (do (meal-domain/delete id)
                 (resp/redirect "/allMeals")))

           (GET "/domain/meals/:id/update" [id]
             (controller/updateMeal id))

           (POST "/domain/meals/:meal_id/update" [& params]
             (do (meal-domain/update (:meal_id params) params)
                 (resp/redirect "/allMeals")))
           )

(defroutes app-routes
           public-routes
           (route/not-found "404. Page not found"))

(def -main
  (handler/site app-routes))
