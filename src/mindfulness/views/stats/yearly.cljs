(ns mindfulness.views.stats.yearly
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]
            [mindfulness.views.stats.utilities :refer [generate-chart]]))


(def year-value-map
  {:0 {:value 0 :count 0}
   :1 {:value 0 :count 0}
   :2 {:value 0 :count 0}
   :3 {:value 0 :count 0}
   :4 {:value 0 :count 0}
   :5 {:value 0 :count 0}
   :6 {:value 0 :count 0}
   :7 {:value 0 :count 0}
   :8 {:value 0 :count 0}
   :9 {:value 0 :count 0}
   :10 {:value 0 :count 0}
   :11 {:value 0 :count 0}})

(defn create-year-values [enteries]
  (loop [index 0
         year-map year-value-map]
      (if (= (count enteries) index)
        [ (:0 year-map)  (:1 year-map)  (:2 year-map)  (:3 year-map)  (:4 year-map)  (:5 year-map) (:6 year-map)  (:7 year-map)  (:8 year-map)  (:9 year-map)  (:10 year-map) (:11 year-map)  ]
        (recur (+ index 1)
               (-> year-map
                 (update-in  [(keyword (str (.getMonth (js/Date. (:date (nth enteries index)))))) :value] + (int (:overall (nth enteries index))))
                 (update-in [(keyword (str (.getMonth (js/Date. (:date (nth enteries index)))))) :count] inc))))))

(defn Yearly [active enteries]
  [:div.SubPage {:class active}
    [:h2 {:style {:padding-left "15px" :padding-bottom "15px"}} "Average By Month"]
    [:canvas#Yearly-chart {:width "400px" :height "400px"}]
    (generate-chart "Yearly-chart" (map #(/ (:value %) (:count %)) (create-year-values enteries)) :year)])
