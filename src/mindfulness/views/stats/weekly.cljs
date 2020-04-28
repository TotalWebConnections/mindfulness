(ns mindfulness.views.stats.weekly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]
            [mindfulness.services.scripts.dates :refer [format-date-object]]))

(defn is-date-in-week [week date]
  (some  #(= date %) week))

(defn get-week-value [dateValue item]
  (= dateValue (:date item)))

(defn generate-week-values [week currentWeek]
  (map (fn [dayOfWeek]
    (let [currentDay (filter #(get-week-value dayOfWeek %) currentWeek)]
      (if (> (count currentDay) 0)
        (first currentDay)
        "N/A"))) week))

(defn get-week []
  "returns the dates in the current week sun-mon"
  (let [curr (js/Date.)]
    (loop [date-offset 0
           dates []]
      (if (> date-offset 6)
        dates
      (recur (+ 1 date-offset)
             (conj dates (format-date-object (js/Date. (.setDate curr (+ date-offset (- (.getDate curr) (.getDay curr))))))))))))

(defn Weekly [active enteries]
  (let [week (get-week)
        currentWeek (filter #(is-date-in-week week (:date %)) enteries)
        graphValues (generate-week-values week currentWeek)]
    [:div.SubPage {:class active}
      [:canvas#Weekly-chart {:width "400px" :height "400px"}]
      (generate-chart "Weekly-chart")
      [:p "top positive words"]
      [:p "top negitive words"]]))
