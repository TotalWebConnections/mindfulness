(ns mindfulness.services.state.dispatcher
  (:require [mindfulness.services.state.global :refer [app-state update-active-view update-home-view]]
            [mindfulness.services.state.textstate :refer [update-state-text]]))

; As we need more mutations for state we can add them here - Handle state change
; calls the correct method based on the type passed in
(defmulti handle-state-change (fn [action] (:type action)))
  (defmethod handle-state-change "update-active-view"
    [action]
    (update-active-view app-state (:value action)))
  (defmethod handle-state-change "update-home-view"
    [action]
    (update-home-view app-state (:value action)))