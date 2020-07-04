(ns mindfulness.components.tutorial
  (:require [reagent.core :as reagent :refer [atom]]
            [mindfulness.services.state.dispatcher :refer [handle-state-change]]))


(def numberOfSlides 3)
(def slideImages ["./tutorial/screen1.jpg" "./tutorial/screen2.jpg"  "./tutorial/screen3.jpg"])


(defn advance-slide [slideNumberRef]
  (swap! slideNumberRef inc)
  (if (= @slideNumberRef numberOfSlides)
    (handle-state-change {:type "hide-tutorial" :value false})))


(defn Tutorial [active]
  (let [slideNumber (atom 0)]
    (fn [active]
      [:div.Tutorial.Page {:class (if active "active" "")}
       [:button {:on-click #(advance-slide slideNumber)} "Next Step"]
       (doall (map-indexed (fn [index slide]
                             (if (= index @slideNumber)
                               [:div.slide.active {:key index}
                                [:img {:src slide :style {:width "100%"}}]]
                               [:div.slide {:key index}
                                [:img {:src slide :style {:width "100%"}}]])) slideImages))])))

