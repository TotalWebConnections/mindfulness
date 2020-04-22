(ns mindfulness.views.stats.monthly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))

(defn Monthly [active]
  [:div.SubPage {:class active}
    [:h2 "monthly page"]])
