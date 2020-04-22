(ns mindfulness.services.state.dispatcher
  (:require [mindfulness.services.state.global :refer [app-state update-active-view update-home-view update-flow-view update-individual-entry update-stats-view]]
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
  (defmethod handle-state-change "update-flow-view"
    [action]
    (update-flow-view app-state (:value action)))
  (defmethod handle-state-change "update-stats-view"
    [action]
    (update-stats-view app-state (:value action)))
  (defmethod handle-state-change "update-individual-entry"
    [action]
    (update-individual-entry app-state (:value action)))
