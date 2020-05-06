(ns mindfulness.views.account
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.services.state.data :refer [dailies]]))



(defn Account [active]
  [:div.Page.Account {:class active}
    [:p {:on-click #(handle-state-change {:type "update-active-view" :value ""})}"Go Back"]
    [:h2 "I'm the account page"]
    [:p "Total Days Of Mindfulness"]
    [:p "longest Streak!"]

    [:div.daily
    [ :p (nth dailies (rand-int (count dailies)))]]])
