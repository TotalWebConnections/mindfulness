(ns mindfulness.views.stats
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            ["chart.js" :as Chart]))


; TODO month View

(def graph-labels {
  :week [
    "mon"
    "tue"
    "wed"
    "thu"
    "fri"
    "sat"
    "sun"
  ] ; months are dynamic
  :year {
    "jan"
    "feb"
    "mar"
    "apr"
    "may"
    "jun"
    "jul"
    "aug"
    "sep"
    "oct"
    "nov"
    "dec"
  }
})

(defn generate-chart []
  (js/setTimeout
    #(let [ctx (.getElementById js/document "Chart")]
       (Chart. ctx (clj->js {
        :type "line"
        :data {
          :labels (range 6)
          :datasets [{
            :label "# of Votes"
            :data [9 7 3 5 2 3]
          }]
        }
       }))

  ) 1000))

(defn Stats [active enteries]
  [:div.Page {:class active}
    [:div.StatsHeader
      [:p {:on-click #(handle-state-change {:type "update-active-view" :value ""})} "Go Back"]]
    [:div.Period-wrapper
      [:div.Period-wrapper-inner
        [:h3 "Yearly"]
        [:h3 "Monthly"]
        [:h3 "Weekly"]]]
    [:canvas#Chart {:width "400px" :height "400px"}]
    (generate-chart)
    [:p "Top Positive"]
    [:p "Top negitive"]])
