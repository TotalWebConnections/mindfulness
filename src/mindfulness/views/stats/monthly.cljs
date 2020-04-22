(ns mindfulness.views.stats.monthly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]))

(defn Monthly [active]
  [:div.SubPage {:class active}
    [:h2 "monthly page"]
    [:canvas#Monthly-chart {:width "400px" :height "400px"}]
    (generate-chart "Monthly-chart")
    [:p "top positive words"]
    [:p "top negitive words"]])
