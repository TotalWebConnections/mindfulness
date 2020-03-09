(ns mindfulness.core
    (:require [reagent.core :as reagent :refer [atom]]
              [mindfulness.views.home :refer [Home]]
              [mindfulness.views.day :refer [Day]]
              [mindfulness.views.timeline :refer [Timeline]]
              [mindfulness.services.state.global :refer [app-state]]
              [mindfulness.services.state.dispatcher :refer [handle-state-change]]
              [mindfulness.components.nav :refer [Nav]]))

(enable-console-print!)

(defn core []
  [:div.Main
    (print @app-state)
    ; [:p {:on-click #(handle-state-change {:type "update-state-text" :value "Test Text Here"})} "Click to update state text"]
    [:div.Period-wrapper
      [:div.Period-wrapper-inner
        [:h3 {:on-click #(handle-state-change {:type "update-home-view" :value "home"})} "Today"]
        [:h3 {:on-click #(handle-state-change {:type "update-home-view" :value "timeline"})}  "Past"]]]
    [:div.Home-Wrapper
      [Home (:home (:home-view-active @app-state))]
      [Timeline (:timeline (:home-view-active @app-state))]
      [:div {:style {:clear "both"}}]
    ]
    [Nav]
    ; [Timeline (:timeline (:active-page @app-state))]
    ])

(reagent/render-component [core]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
