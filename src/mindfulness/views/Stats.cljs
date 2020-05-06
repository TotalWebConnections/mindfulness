(ns mindfulness.views.stats
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.weekly :refer [Weekly]]
            [mindfulness.views.stats.monthly :refer [Monthly]]
            [mindfulness.views.stats.yearly :refer [Yearly]]))

(defn Stats [active enteries stats-views]
  [:div.Page {:class active}
    [:div.StatsHeader
      [:svg {:viewBox "0 0 448 512" :class "backArrow" :on-click #(handle-state-change {:type "update-active-view" :value ""})}
        [:path {:fill "white" :d "M134.059 296H436c6.627 0 12-5.373 12-12v-56c0-6.627-5.373-12-12-12H134.059v-46.059c0-21.382-25.851-32.09-40.971-16.971L7.029 239.029c-9.373 9.373-9.373 24.569 0 33.941l86.059 86.059c15.119 15.119 40.971 4.411 40.971-16.971V296z"}
        ]]]
    [:div.Period-wrapper.stats-period
      [:div.Period-wrapper-inner.stats-period
        [:h3.Period {:class (:weekly stats-views)  :on-click #(handle-state-change {:type "update-stats-view" :value "weekly"})} "Weekly"]
        [:h3.Period {:class (:monthly stats-views) :on-click #(handle-state-change {:type "update-stats-view" :value "monthly"})} "Monthly"]
        [:h3.Period {:class (:yearly stats-views)  :on-click #(handle-state-change {:type "update-stats-view" :value "yearly"})} "Yearly"]]]
    [:div.Stats-wrapper
      [Weekly (:weekly stats-views) enteries]
      [Monthly (:monthly stats-views) enteries]
      [Yearly (:yearly stats-views) enteries]]])
