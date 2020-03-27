(ns mindfulness.views.timeline
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))



(defn Timeline [active enteries]
  (print enteries)
  [:div.SubPage {:class active}
    [:div#slider
      (for [entry enteries]
        [:div.Timeline__entey
          [:h2 (:overall entry)]])]])



; (let [slider (.getElementById js/document "slider")]
;   (.slick slider))