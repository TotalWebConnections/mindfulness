(ns mindfulness.views.stats
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.weekly :refer [Weekly]]
            [mindfulness.views.stats.monthly :refer [Monthly]]
            [mindfulness.views.stats.yearly :refer [Yearly]]))

(defn Stats [active enteries stats-views]
  [:div.Page {:class active}
    [:div.StatsHeader
      [:p {:on-click #(handle-state-change {:type "update-active-view" :value ""})} "Go Back"]]
    [:div.Period-wrapper.stats-period
      [:div.Period-wrapper-inner.stats-period
        [:h3.Period {:class (:weekly stats-views)  :on-click #(handle-state-change {:type "update-stats-view" :value "weekly"})} "Weekly"]
        [:h3.Period {:class (:monthly stats-views) :on-click #(handle-state-change {:type "update-stats-view" :value "monthly"})} "Monthly"]
        [:h3.Period {:class (:yearly stats-views)  :on-click #(handle-state-change {:type "update-stats-view" :value "yearly"})} "Yearly"]]]
    [:div.Stats-wrapper
      [Weekly (:weekly stats-views) enteries]
      [Monthly (:monthly stats-views) enteries]
      [Yearly (:yearly stats-views) enteries]]])
