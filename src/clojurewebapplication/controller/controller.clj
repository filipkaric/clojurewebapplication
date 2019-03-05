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

(defn about []
  (render-template "about" {}))

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
                            :menu_type (menu-domain/allMenuTypes)
                            :chef (menu-domain/allChefs)}))

(defn updateMenu [id]
  (render-template "updateMenu" {:menu (menu-domain/getMenu id)
                                 :menu_type (menu-domain/allMenuTypes)
                                 :chef (menu-domain/allChefs)}))
(defn showMenu [id]
  (render-template "printMenu" {:menu (menu-domain/getMenu id)
                                :menu_type (menu-domain/allMenuTypes)
                                :chef (menu-domain/allChefs)}))


(defn insertMenu []
  (render-template "insertMenu" {:meal_type (meal-domain/allMealTypes)
                                 :menu_type (menu-domain/allMenuTypes)
                                 :chef (menu-domain/allChefs)}))
