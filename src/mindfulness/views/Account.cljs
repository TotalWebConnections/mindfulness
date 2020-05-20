(ns mindfulness.views.account
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.services.state.data :refer [dailies]]))



(defn Account [active enteries]
  [:div.Page.Account {:class active}
    [:div.AccountHeader
      [:svg {:viewBox "0 0 448 512" :class "backArrow" :on-click #(handle-state-change {:type "update-active-view" :value ""})}
        [:path {:fill "white" :d "M134.059 296H436c6.627 0 12-5.373 12-12v-56c0-6.627-5.373-12-12-12H134.059v-46.059c0-21.382-25.851-32.09-40.971-16.971L7.029 239.029c-9.373 9.373-9.373 24.569 0 33.941l86.059 86.059c15.119 15.119 40.971 4.411 40.971-16.971V296z"}
        ]]]
    [:h2 {:style {:text-align "center" :margin "25px 0"}} "Your Account"]
    [:p {:style {:padding-left "15px"}} (str "Total Days Of Mindfulness: " (count enteries))]
    [:div.daily
      [ :p (nth dailies (rand-int (count dailies)))]]])
