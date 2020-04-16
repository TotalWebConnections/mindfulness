(ns mindfulness.views.individual
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))



(defn Individual [active entry]
  [:div.Page.Individual {:class active}
    [:p {:on-click #(handle-state-change {:type "update-home-view" :value "timeline"})}"Go Back"]
    [:h2 "im a individual page to review"]
    [:h3 (:date entry)]])
