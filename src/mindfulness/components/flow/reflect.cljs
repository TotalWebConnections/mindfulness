(ns mindfulness.components.flow.reflect)


(defn Reflect [active handle-save]
  (let [reflect-value (atom "")]
    (fn [active handle-save]
      [:div.SubPage.SubPageFlow.Reflect {:class active}
        [:h3 "Here's a Fun Promp To Work Through"]
        [:textarea {:style {:resize "none"} :on-change #(reset! reflect-value (-> % .-target .-value))}]
        [:button {:on-click #(handle-save @reflect-value)}"Save!"]])))
