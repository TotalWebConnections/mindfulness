(ns mindfulness.views.account
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))



(defn Account [active]
  [:div.Page {:class active}
    [:p {:on-click #(handle-state-change {:type "update-active-view" :value ""})}"Go Back"]
    [:h2 "I'm the account page"]])