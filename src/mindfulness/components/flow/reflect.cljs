(ns mindfulness.components.flow.reflect)


(defn Reflect [active on-advance handle-save]
  [:div.SubPage.SubPageFlow.Reflect {:class active}
    [:h3 "Here's a Fun Promp To Work Through"]
    [:textarea {:style {:resize "none"}}]])
