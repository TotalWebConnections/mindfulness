(ns mindfulness.views.stats.utilities)



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

(defn generate-chart []
  (js/setTimeout
    #(let [ctx (.getElementById js/document "Chart")]
       (Chart. ctx (clj->js {
        :type "line"
        :data {
          :labels (range 6)
          :datasets [{
            :label "# of Votes"
            :data [9 7 3 5 2 3]
          }]
        }
       }))

  ) 1000))
