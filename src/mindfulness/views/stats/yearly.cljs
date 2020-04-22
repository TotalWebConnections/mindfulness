(ns mindfulness.views.stats.yearly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))


(defn Yearly [active]
  [:div.SubPage {:class active}
    [:h2 "yearly page"]])
