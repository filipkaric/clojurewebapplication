(ns clojurewebapplication.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
            [ring.util.response :as resp]
            [clojurewebapplication.domain.meal :as meal-domain]
            [clojurewebapplication.domain.dailymenu :as menu-domain]
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
           (GET "/insertMeal" [] (controller/insertMeal))
           (route/resources "/")
           (GET "/allMenus" [] (controller/allMenus))
           (route/resources "/")
           (GET "/insertMenu" [] (controller/insertMenu))
           (route/resources "/")

           (GET "/domain/meals/:id/delete" [id]
             (do (meal-domain/delete id)
                 (resp/redirect "/allMeals")))

           (GET "/domain/meals/:id/update" [id]
             (controller/updateMeal id))

           (POST "/domain/meals/:meal_id/update" [& params]
             (do (meal-domain/update (:meal_id params) params)
                 (resp/redirect "/allMeals")))

           (POST "/domain/meals/insert" [& params]
             (do (meal-domain/insertMeal params)
                 (resp/redirect "/allMeals")))

           (GET "/domain/menus/:id/delete" [id]
             (do (menu-domain/deleteMenu id)
                 (resp/redirect "/allMenus")))

           (GET "/domain/menus/:id/update" [id]
             (controller/updateMenu id))

           (POST "/domain/menus/:menu_id/update" [& params]
             (do (menu-domain/updateMenu (:menu_id params) params)
                 (resp/redirect "/allMenus")))

           (POST "/domain/menus/insert" [& params]
             (do (menu-domain/insertMenu params)
                 (resp/redirect "/allMenus")))
           )

(defroutes app-routes
           public-routes
           (route/not-found "404. Page not found"))

(def -main
  (handler/site app-routes))
