(ns mindfulness.components.flow.blurb)


(defn Blurb [active on-advance]
  [:div.SubPage.SubPageFlow.Blurb {:class active}
  [:h3 "Write One Positive Thing That Happened Today"]
  [:textarea {:style {:resize "none"}}]
  [:h3 "What Is One Thing You'd Change About Today?"]
  [:textarea {:style {:resize "none"}}]
  [:button {:on-click #(on-advance "reflect")} "Next Step"]])
