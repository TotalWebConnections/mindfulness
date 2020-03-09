(ns mindfulness.views.day
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.services.state.global :refer [app-state]]
            [mindfulness.components.flow.blurb :refer [Blurb]]
            [mindfulness.components.flow.overall :refer [Overall]]
            [mindfulness.components.flow.reflect :refer [Reflect]]))


(defn advance-to-next-step [step]
  "advances to the next step in flow - takes the string rep for the state"
  (handle-state-change {:type "update-flow-view" :value step}))


(defn Day [active app-state]
  [:div.Page.Flow {:class active}
    [:h2 "Today 'DATE'"]
    [:div.Flow-wrapper
      [Blurb (:blurb (:flow-view-active @app-state)) advance-to-next-step]
      [Overall (:overall (:flow-view-active @app-state)) advance-to-next-step]
      [Reflect (:reflect (:flow-view-active @app-state)) advance-to-next-step]]])