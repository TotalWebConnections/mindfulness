(ns mindfulness.views.timeline
  (:require [mindfulness.services.state.dispatcher :refer [handle-state-change]]))

(def entry-sizing 200)
(def entry-padding 75)
(def base-width 375) ;iosx change in prod

(defn generate-slide-padding [index]
  (str (+ (- (/ base-width 2) (/ entry-sizing 2)) (* index entry-padding) (* index entry-sizing)) "px"))


(defn Timeline [active enteries]
  [:div.SubPage.Timeline {:class active}
    [:div#slider {:style {:width "calc(vw * 3)"}}
      (loop [index 0
             output ()]
        (if (= index (count enteries))
          output
          (let [entry (nth enteries index)]
              (recur (inc index) (conj output
                [:div.Timeline__entry {:style {:left (generate-slide-padding index)}}
                  [:h2 (:overall entry)]])))))]])
