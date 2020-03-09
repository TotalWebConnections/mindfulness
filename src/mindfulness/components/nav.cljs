(ns mindfulness.components.nav
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))


(defn Nav [text]
  [:div.Nav
    [:p {:on-click #(handle-state-change {:type "update-active-view" :value ""})} "H" ]
    [:p {:on-click #(handle-state-change {:type "update-active-view" :value "stats"})} "S"]
    [:p {:on-click #(handle-state-change {:type "update-active-view" :value "account"})} "A"]])