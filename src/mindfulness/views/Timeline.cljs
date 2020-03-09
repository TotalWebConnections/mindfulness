(ns mindfulness.views.timeline
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))



(defn Timeline [active]
  (print active)
  [:div.SubPage {:class active}
    [:h2 "I'm the timelie pages"]])