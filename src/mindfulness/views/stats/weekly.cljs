(ns mindfulness.views.stats.weekly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]))

(defn Weekly [active]
  [:div.SubPage {:class active}
    [:h2 "weekly page"]
    [:canvas#Weekly-chart {:width "400px" :height "400px"}]
    (generate-chart "Weekly-chart")
    [:p "top positive words"]
    [:p "top negitive words"]])
