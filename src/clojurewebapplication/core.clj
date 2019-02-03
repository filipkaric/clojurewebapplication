(ns clojurewebapplication.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
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
           )

(defroutes app-routes
           public-routes
           (route/not-found "404. Page not found"))

(def -main
  (handler/site app-routes))
