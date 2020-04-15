(ns mindfulness.views.day
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.services.state.global :refer [app-state]]
            [mindfulness.components.flow.blurb :refer [Blurb]]
            [mindfulness.components.flow.overall :refer [Overall]]
            [mindfulness.components.flow.reflect :refer [Reflect]]
            [mindfulness.services.persistence.storage :refer [save-day-entry]]))


(defn advance-to-next-step [step]
  "advances to the next step in flow - takes the string rep for the state"
  (handle-state-change {:type "update-flow-view" :value step}))

(defn update-reflection [state key value]
  (swap! state conj {(keyword key) value}))

(defn handle-save [current-value prompt-value]
  (save-day-entry (conj @current-value {:reflect prompt-value})))

(def dates ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"])

(defn get-number-suffix [number]
  (cond
    (= number 1) (str number "st")
    (= number 2) (str number "nd")
    (= number 3) (str number "rd")
    :else (str number "th")))

(defn generate-date-text []
  (let [date (js/Date.)]
    (str (nth dates (.getMonth date))" "(get-number-suffix (.getDay date)))  ))

(defn Day [active app-state]
  (let [todays-reflection (atom {:overall nil :good "" :bad "" })] ; we don't inclue prompt as we can just conj that to the saved object at the end
    (fn [active app-state]
      [:div.Page.Flow {:class active}
        [:div.Flow-header
          [:h1 (str "Today: " (generate-date-text))]
          [:h1 "Let's Reflect On Today!"]]
        [:div.Flow-wrapper
          [Overall (:overall (:flow-view-active @app-state)) advance-to-next-step (partial update-reflection todays-reflection)]
          [Blurb (:blurb (:flow-view-active @app-state)) advance-to-next-step (partial update-reflection todays-reflection)]
          [Reflect (:reflect (:flow-view-active @app-state)) (partial handle-save todays-reflection)]]])))
