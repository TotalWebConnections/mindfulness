(ns mindfulness.views.stats.utilities
    (:require ["chart.js" :as Chart]))



(def graph-labels {
  :week [
    "mon"
    "tue"
    "wed"
    "thu"
    "fri"
    "sat"
    "sun"
  ] ; months are dynamic
  :year {
    "jan"
    "feb"
    "mar"
    "apr"
    "may"
    "jun"
    "jul"
    "aug"
    "sep"
    "oct"
    "nov"
    "dec"
  }
})

(defn generate-chart [id]
  (js/setTimeout
    #(let [ctx (.getElementById js/document id)]
       (Chart. ctx (clj->js {
        :type "line"
        :data {
          :labels (:week graph-labels)
          :datasets [{
            :label "# of Votes"
            :data [9 7 3 5 2 3]
          }]
        }
       }))

  ) 1000))


(defn get-top-positive-words [enteries])


(defn get-top-negative-words [enteries])
