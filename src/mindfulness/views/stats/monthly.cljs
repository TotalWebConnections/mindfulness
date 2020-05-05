(ns mindfulness.views.stats.monthly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]
            [mindfulness.services.scripts.dates :refer [format-date-object]]))

(defn is-date-in-month [month date]
  (some  #(= date %) month))

(defn get-days-in-month [month year]
  (.getDate (js/Date. year month 0)))


(defn get-month []
  "returns the dates in the current month"
  (let [curr (js/Date.)
        month (+ 1 (.getMonth curr))
        year (.getFullYear curr)
        monthDays (get-days-in-month month year)]
    (loop [date-offset 1
           dates []]
      (if (> date-offset monthDays)
        dates
        (recur (+ 1 date-offset)
               (conj dates (format-date-object (js/Date. year (- month 1) date-offset))))))))

; (defn generate-month-values [month currentMonth]
;   (map (fn [dayOfMonth]
;     (let [currentDay (filter #(get-week-value dayOfWeek %) currentWeek)]
;       (if (> (count currentDay) 0)
;         (first currentDay)
;         {:overall "N/A"}))) week))

(defn Monthly [active enteries]
  (let [month (get-month)
        currentMonth (filter #(is-date-in-month month (:date %)) enteries)]
    [:div.SubPage {:class active}
      [:h2 "monthly page"]
      [:canvas#Monthly-chart {:width "400px" :height "400px"}]
      (generate-chart "Monthly-chart" (map #(:overall %) currentMonth) month)
      [:p "top positive words"]
      [:p "top negitive words"]]))
