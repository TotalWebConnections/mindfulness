(ns mindfulness.views.stats.utilities
    (:require ["chart.js" :as Chart]))



(def graph-labels {
  :week [
    "sun"
    "mon"
    "tue"
    "wed"
    "thu"
    "fri"
    "sat"
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

(defn generate-chart [id data]
  (js/setTimeout
    #(let [ctx (.getElementById js/document id)]
       (Chart. ctx (clj->js {
        :type "line",
        :options {
         :scales {
            :yAxes [{
              :ticks {:beginAtZero true :stepSize 1 :suggestedMax 10}
            }]
            }
        }
        :data {
          :labels (:week graph-labels)
          :datasets [{
            :label "Daily Value"
            :data data
          }]
        }
       }))

  ) 1000))


(defn get-top-positive-words [enteries])


(defn get-top-negative-words [enteries])
