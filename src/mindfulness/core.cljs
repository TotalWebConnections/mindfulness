(ns mindfulness.core
    (:require [reagent.core :as reagent :refer [atom]]
              [mindfulness.views.home :refer [Home]]
              [mindfulness.views.day :refer [Day]]
              [mindfulness.views.stats :refer [Stats]]
              [mindfulness.views.account :refer [Account]]
              [mindfulness.views.timeline :refer [Timeline]]
              [mindfulness.services.state.global :refer [app-state]]
              [mindfulness.services.state.dispatcher :refer [handle-state-change]]
              [mindfulness.components.nav :refer [Nav]]
              [mindfulness.views.complete :refer [Complete]]))

(enable-console-print!)

(defn core []
  [:div.Main
    (print @app-state)
    [Stats (:stats (:active-page @app-state))]
    [Account (:account (:active-page @app-state))]
    [Day (:day (:active-page @app-state)) app-state]
    [Complete (:complete (:active-page @app-state)) app-state]
    [:div.Period-wrapper
      [:div.Period-wrapper-inner
        [:h3.Period {:class (:home (:home-view-active @app-state)) :on-click #(handle-state-change {:type "update-home-view" :value "home"})} "Today"]
        [:h3.Period {:class (:timeline (:home-view-active @app-state)) :on-click #(handle-state-change {:type "update-home-view" :value "timeline"}) :style {:text-align "right"}}  "Past"]]]
    [:div.Home-Wrapper
      [Home (:home (:home-view-active @app-state))]
      [Timeline (:timeline (:home-view-active @app-state))]
      [:div {:style {:clear "both"}}]
    ]
    [Nav]])

(reagent/render-component [core]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
