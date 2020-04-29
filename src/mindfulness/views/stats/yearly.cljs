(ns mindfulness.views.stats.yearly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]))


(defn Yearly [active]
  [:div.SubPage {:class active}
    [:h2 "yearly page"]
    [:canvas#Yearly-chart {:width "400px" :height "400px"}]
    (generate-chart "Yearly-chart" [])
    [:p "top positive words"]
    [:p "top negitive words"]])
