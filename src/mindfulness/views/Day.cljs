(ns mindfulness.views.day
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.services.state.global :refer [app-state]]
            [mindfulness.components.flow.blurb :refer [Blurb]]
            [mindfulness.components.flow.overall :refer [Overall]]
            [mindfulness.components.flow.reflect :refer [Reflect]]))


(defn advance-to-next-step [step]
  "advances to the next step in flow - takes the string rep for the state"
  (handle-state-change {:type "update-flow-view" :value step}))

(defn update-reflection [state key value]
  (swap! state conj {(keyword key) value}))

(defn handle-save [current-value prompt-value]
  ; (do-save (conj current-value {:prompt promp-value}))
)


(defn Day [active app-state]
  (let [todays-reflection (atom {:overall nil :good "" :bad "" })] ; we don't inclue prompt as we can just conj that to the saved object at the end
    (fn [active app-state]
      (print @todays-reflection)
      [:div.Page.Flow {:class active}
        [:div.Flow-header
          [:h1 "Today 'DATE'"]
          [:h1 "Let's Reflect On Today!"]]
        [:div.Flow-wrapper
          [Overall (:overall (:flow-view-active @app-state)) advance-to-next-step (partial update-reflection todays-reflection)]
          [Blurb (:blurb (:flow-view-active @app-state)) advance-to-next-step (partial update-reflection todays-reflection)]
          [Reflect (:reflect (:flow-view-active @app-state)) advance-to-next-step (partial handle-save todays-reflection)]]])))
