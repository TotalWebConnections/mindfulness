(ns mindfulness.views.home
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.services.scripts.dates :refer [is-entry-today]]))



(defn Home [active enteries]
  [:div.SubPage {:class active}
    (if (is-entry-today enteries)
      [:div.Home-callout
        [:p "You've Already Reflected Today"]]
      [:div.Home-callout {:on-click #(handle-state-change {:type "update-active-view" :value "day"})}
        [:svg {:viewBox "0 0 448 512"}
          [:path {:fill "#f4f7f9"  :d "M400 32H48C21.5 32 0 53.5 0 80v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V80c0-26.5-21.5-48-48-48zm-32 252c0 6.6-5.4 12-12 12h-92v92c0 6.6-5.4 12-12 12h-56c-6.6 0-12-5.4-12-12v-92H92c-6.6 0-12-5.4-12-12v-56c0-6.6 5.4-12 12-12h92v-92c0-6.6 5.4-12 12-12h56c6.6 0 12 5.4 12 12v92h92c6.6 0 12 5.4 12 12v56z"}]]
        [:p "Reflect On Today"]])
    [:h3 "Current Streak 0"]])