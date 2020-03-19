(ns mindfulness.services.scripts.dates)

(defn format-date-object [date]
  (let [year (.getFullYear date)
        month (.padStart (.toString (+ 1 (.getMonth date))) 2 "0")
        day (.padStart (.toString (.getDate date)) 2 "0")]
    (str month "/" day "/" year)))


(defn is-entry-today [enteries]
  (let [todays-date (format-date-object (js/Date.))]
    (if
      (> (count (filter (fn [entry]
        (= (:date entry) todays-date)) enteries)) 0)
      true
      false)))