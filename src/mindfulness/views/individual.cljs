(ns mindfulness.views.individual
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))



(defn Individual [active entry]
  [:div.Page.Individual {:class active}
    [:div.individualHeader
    [:svg {:viewBox "0 0 448 512" :class "backArrow" :on-click #(handle-state-change {:type "update-home-view" :value "timeline"})}
      [:path {:fill "white" :d "M134.059 296H436c6.627 0 12-5.373 12-12v-56c0-6.627-5.373-12-12-12H134.059v-46.059c0-21.382-25.851-32.09-40.971-16.971L7.029 239.029c-9.373 9.373-9.373 24.569 0 33.941l86.059 86.059c15.119 15.119 40.971 4.411 40.971-16.971V296z"}
      ]]]

    [:div.individual.body
      [:h3 (:date entry)]
      [:h4 (str "Your Overall Rating: " (:overall entry))]
      [:h4 (str "A Good Thing: " (:good entry))]
      [:h4 (str "A Not So Good Thing: " (:bad entry))]
      [:h4 (str "Reflection: " (:reflect entry))]]])
