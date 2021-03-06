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


(defn setup-listeners []
  (.addEventListener (.getElementById js/document "Timeline") "touchstart" handle-touch-start false)
  (.addEventListener (.getElementById js/document "Timeline") "touchmove" handle-touch-move false)
  (.addEventListener (.getElementById js/document "Timeline") "touchend" handle-touch-end false))

(defn handle-open-individual [payload]

  (handle-state-change {:type "update-individual-entry" :value payload})
  (handle-state-change {:type "update-home-view" :value "individual"}))


(defn Timeline [active enteries]
  (let [listeners-set (atom false)]
    (fn [active enteries]
      (if (and (not @listeners-set) (= active "active")) ; sets our listeners on first visit only
        (do
          (setup-listeners)
          (reset! listeners-set true)))
      [:div.SubPage.Timeline {:class active :id "Timeline"}
        [:div#slider {:style {:width "calc(vw * 3)"}}
          (loop [index 0
                 output ()]
            (if (= index (count enteries))
              output
              (let [entry (nth enteries index)]
                  (recur (inc index) (conj output
                    [:div.Timeline__entry {:style {:left (generate-slide-padding index)} :key index}
                      [:h2 (:date entry)]
                      [:h3 (str "Overall "(:overall entry))]
                      [:button {:on-click #(handle-open-individual entry)} "Explore"]])))))]])))
