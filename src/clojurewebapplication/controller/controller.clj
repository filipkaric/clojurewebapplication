(ns clojurewebapplication.controller.controller
  (:require
    [clostache.parser :as clostache]
    [clojurewebapplication.domain.meal :as meal-domain]
    [clojurewebapplication.domain.dailymenu :as menu-domain]
    ))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
           (str "pages/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn home []
  (render-template "homepage" {}))

(defn allMeals []
  (render-template "meals" {:meal (meal-domain/allMeals)
                            :meal_type (meal-domain/allMealTypes)}))

(defn updateMeal [id]
  (render-template "updateMeal" {:meal (meal-domain/get id)
                                 :meal_type (meal-domain/allMealTypes)}))

(defn insertMeal []
  (render-template "insertMeal" {:meal_type (meal-domain/allMealTypes)}))

(defn allMenus []
  (render-template "menus" {:menu (menu-domain/allMenus)
                            :menu_type (menu-domain/allMenuTypes)}))

(defn updateMenu [id]
  (render-template "updateMeal" {:meal (meal-domain/get id)
                                 :meal_type (meal-domain/allMealTypes)}))

(defn insertMenu []
  (render-template "insertMeal" {:meal_type (meal-domain/allMealTypes)}))
