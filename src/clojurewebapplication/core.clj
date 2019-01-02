(ns clojurewebapplication.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
            )
  )

(defroutes public-routes
           )

(defroutes app-routes
           public-routes
           (route/not-found "404. Page not found"))

(def -main
  (handler/site app-routes))
