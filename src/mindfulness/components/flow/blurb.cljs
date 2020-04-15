(ns mindfulness.components.flow.blurb)

(defn handle-advance [on-advance update-state value]
  (update-state "good" (:good value))
  (update-state "bad" (:bad value))
  (on-advance "reflect"))

(defn Blurb [active on-advance update-state]
  (let [blurb-values (atom {:good nil :bad nil})]
    (fn [active on-advance update-state]
      [:div.SubPage.SubPageFlow.Blurb {:class active}
      [:h3 "Write One Positive Thing That Happened Today"]
      [:textarea {:style {:resize "none"} :on-change #(swap! blurb-values conj {:good (-> % .-target .-value)})}]
      [:h3.blurbTitle2 "What Is One Thing You'd Change About Today?"]
      [:textarea {:style {:resize "none"} :on-change #(swap! blurb-values conj {:bad (-> % .-target .-value)})}]
      [:button {:on-click #(handle-advance on-advance update-state @blurb-values)} "Next Step"]])))
