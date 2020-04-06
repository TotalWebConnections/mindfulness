(ns mindfulness.views.timeline
  (:require [reagent.core :as reagent :refer [atom]]
            [mindfulness.services.state.dispatcher :refer [handle-state-change]]))

(def entry-sizing 200)
(def entry-padding 75)
(def base-width 375) ;iosx change in prod

(def sliderStartX (atom 0))
(def currentSlideDifference (atom 0))
(def endOfLastSwipe (atom 0)) ; required or each swipe will reset the past one

(defn generate-slide-padding [index]
  (str (+ (* 1 @currentSlideDifference) (- (/ base-width 2) (/ entry-sizing 2)) (* index entry-padding) (* index entry-sizing)) "px"))



(defn get-touches [evt]
  (.-touches evt))

(defn handle-touch-start [evt]
    (reset! sliderStartX (+ (* -1 @endOfLastSwipe) (.-clientX (aget (get-touches evt) 0))))
  )

(defn handle-touch-move [evt]
    (reset! currentSlideDifference (- (.-clientX (aget (get-touches evt) 0)) @sliderStartX))
  )

(defn handle-touch-end [evt]
  (reset! sliderStartX 0)
  (reset! endOfLastSwipe @currentSlideDifference)
)



(.addEventListener js/document "touchstart" handle-touch-start false)
(.addEventListener js/document "touchmove" handle-touch-move false)
(.addEventListener js/document "touchend" handle-touch-end false)



(defn Timeline [active enteries]
  [:div.SubPage.Timeline {:class active}
    [:div#slider {:style {:width "calc(vw * 3)"}}
      (loop [index 0
             output ()]
        (if (= index (count enteries))
          output
          (let [entry (nth enteries index)]
              (print entry)
              (recur (inc index) (conj output
                [:div.Timeline__entry {:style {:left (generate-slide-padding index)} :key index}
                  [:h2 (:date entry)]
                  [:h3 (str "Overall "(:overall entry))]
                  [:button "Explore"]])))))]])
