(ns mindfulness.views.complete
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.env :refer [ADKEY]]))

(defn complete-day []

  (if js/AdMob
      (.prepareInterstitial js/AdMob (clj->js {:adId ADKEY :autoShow true})))
  (handle-state-change {:type "update-active-view" :value ""}))


(defn Complete [active app-state]
  [:div.Page.Page-full {:class active}
    [:div.Complete-page
      [:h1 "Awesome!"]
      [:h2 "Your Journal Has Been Recorded!"]
      [:h2 "Come Back Tommorow To Keep Up Your Streak"]
      [:button {:on-click #(complete-day)} "Complete!"]]])