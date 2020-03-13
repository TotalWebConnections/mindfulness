(ns mindfulness.components.flow.overall
  (:require [reagent.core :as reagent :refer [atom]]))

(defn update-current-value [state val]
  (reset! state val))


(defn handle-advance [on-advance update-state value]
  (update-state "overall" value)
  (on-advance "blurb"))

(defn Overall [active on-advance update-state]
  (let [current-value (atom 5)]
    (fn []
      [:div.SubPage.SubPageFlow.Overall {:class active}
        [:h3 "Overall, How Was Your Day?"]
        [:input {:type "range" :min 1 :max 10 :default-value 5 :on-change #(update-current-value current-value (-> % .-target .-value)) }]
        [:p @current-value]
        [:button {:on-click #(handle-advance on-advance update-state @current-value) } "Next Step"]])))
