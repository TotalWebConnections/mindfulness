(ns mindfulness.components.flow.blurb)


(defn Blurb [active on-advance]
  [:div.SubPage.Blurb {:class active}
  [:h2 "Blurb"]
  [:button {:on-click #(on-advance "overall")} "Next Step"]])