(ns clojurewebapplication.controller.controller
  (:require
    [clostache.parser :as clostache]
    [clojurewebapplication.domain.meal :as meal-domain]
    ))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
           (str "pages/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn home []
  (render-template "homepage" {}))

(defn allMeals []
  (render-template "meals" {:meal (meal-domain/allMeals)}))