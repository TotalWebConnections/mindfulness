(ns mindfulness.services.scripts.dates)

(defn format-date-object [date]
  (let [year (.getFullYear date)
        month (.padStart (.toString (+ 1 (.getMonth date))) 2 "0")
        day (.padStart (.toString (.getDate date)) 2 "0")]
    (str month "/" day "/" year)))