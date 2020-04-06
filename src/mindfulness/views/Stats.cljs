(ns mindfulness.views.stats
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))


; TODO month View


(defn Stats [active]
  [:div.Page {:class active}
    [:p {:on-click #(handle-state-change {:type "update-active-view" :value ""})}"Go Back"]
    [:p "day week mont switcher"]
    [:p "Range graph here going over the day week month"]
    [:p "Top Positive"]
    [:p "Top negitive"]])
