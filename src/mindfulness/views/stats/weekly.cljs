(ns mindfulness.views.stats.weekly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]
            [mindfulness.services.scripts.dates :refer [format-date-object]]))

(defn is-date-in-week [week date]
  (some  #(= date %) week))

(defn get-week []
  "returns the dates in the current week sun-mon"
  (let [curr (js/Date.)]
    (loop [date-offset 0
           dates []]
      (if (> date-offset 6)
        dates
      (recur (+ 1 date-offset) (conj dates (format-date-object (js/Date. (.setDate curr (+ date-offset (- (.getDate curr) (.getDay curr)))))))))
    )))

(defn Weekly [active enteries]
  [:div.SubPage {:class active}
    [:h2 "weekly page"]
    [:canvas#Weekly-chart {:width "400px" :height "400px"}]
    (generate-chart "Weekly-chart")
    [:p "top positive words"]
    [:p "top negitive words"]])
