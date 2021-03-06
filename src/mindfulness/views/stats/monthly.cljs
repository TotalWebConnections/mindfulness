(ns mindfulness.views.stats.monthly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]
            [mindfulness.services.scripts.dates :refer [format-date-object]]))

(defn is-date-in-month [month date]
  (some  #(= date %) month))

(defn get-month-value [dateValue item]
  (= dateValue (:date item)))

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


(defn generate-month-values [month currentMonth]
  (map (fn [dayValue]
    (let [currentDay (filter #(get-month-value dayValue %) currentMonth)]
      (if (> (count currentDay) 0)
        (first currentDay)
        {:overall "N/A"}))) month))

(defn Monthly [active enteries]
  (let [month (get-month)
        currentMonth (filter #(is-date-in-month month (:date %)) enteries)
        monthValues (generate-month-values month currentMonth)]
    [:div.SubPage {:class active}
      [:canvas#Monthly-chart {:width "400px" :height "400px"}]
      (generate-chart "Monthly-chart" (map #(:overall %) monthValues) :month month)]))
