(ns mindfulness.views.individual
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))



(defn Individual [active entry]
  (print entry)
  [:div.Page.Individual {:class active}
    [:div.individualHeader
      [:p {:on-click #(handle-state-change {:type "update-home-view" :value "timeline"})}"Go Back"]]
    [:div.individual.body
      [:h3 (:date entry)]
      [:h4 (str "Your Overall Rating: " (:overall entry))]
      [:h4 (str "A Good Thing: " (:good entry))]
      [:h4 (str "A Not So Good Thing: " (:bad entry))]
      [:h4 (str "Reflection: " (:reflect entry))]]])
