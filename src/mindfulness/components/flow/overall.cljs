(ns mindfulness.components.flow.overall)


(defn Overall [active on-advance]
  [:div.SubPage.Overall {:class active}
  [:h2 "Overall"]
  [:button {:on-click #(on-advance "reflect")} "Next Step"]])