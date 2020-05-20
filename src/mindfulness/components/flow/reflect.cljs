(ns mindfulness.components.flow.reflect
  (:require [mindfulness.services.state.prompts :refer [prompts]]))


(defn Reflect [active handle-save]
  (let [prompt (nth prompts (rand-int (count prompts)))
        reflect-value (atom {:value "" :prompt prompt})]
    (fn [active handle-save]
      [:div.SubPage.SubPageFlow.Reflect {:class active}
        [:h3 "Here's a Fun Promp To Think About: "]
        [:h2.Reflect_prompt (str "\""prompt"\"")]
        [:textarea {:style {:resize "none"} :on-change #(swap! reflect-value conj {:value (-> % .-target .-value)})}]
        [:button {:on-click #(handle-save @reflect-value)}"Save!"]])))
