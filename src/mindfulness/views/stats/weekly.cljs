(ns mindfulness.views.stats.weekly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))

(defn Weekly [active]
  [:div.SubPage {:class active}
    [:h2 "weekly page"]])
