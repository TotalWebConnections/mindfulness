(ns mindfulness.views.stats
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.weekly :refer [Weekly]]
            [mindfulness.views.stats.monthly :refer [Monthly]]
            [mindfulness.views.stats.yearly :refer [Yearly]]
            ["chart.js" :as Chart]))

(defn Stats [active enteries stats-views]
  [:div.Page {:class active}
    [:div.StatsHeader
      [:p {:on-click #(handle-state-change {:type "update-active-view" :value ""})} "Go Back"]]
    [:div.Period-wrapper
      [:div.Period-wrapper-inner
        [:h3 {:on-click #(handle-state-change {:type "update-stats-view" :value "weekly"})} "Weekly"]
        [:h3 {:on-click #(handle-state-change {:type "update-stats-view" :value "monthly"})} "Monthly"]
        [:h3 {:on-click #(handle-state-change {:type "update-stats-view" :value "yearly"})} "Yearly"]]]
    [:div.Stats-wrapper
      [Weekly (:weekly stats-views)]
      [Monthly (:monthly stats-views)]
      [Yearly (:yearly stats-views)]]
    [:canvas#Chart {:width "400px" :height "400px"}]])
