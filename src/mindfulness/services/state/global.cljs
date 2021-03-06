(ns mindfulness.services.state.global
    (:require [reagent.core :as reagent :refer [atom]]))

(defonce app-state (atom {:text "Hello world!"
                          :enteries []
                          :show-tutorial true
                          :individual-entry nil
                          :home-view-active {:timeline false
                                             :home "active"
                                             :individual false}
                          :flow-view-active {
                                             :blurb false
                                             :overall "active"
                                             :reflect false}

                          :stats-view-active {
                                              :weekly "active"
                                              :monthly false
                                              :yearly false}

                          :active-page {
                                        :stats false
                                        :day false}}))

; TODO move these two scrolling functions into a state helper file
; Don't want them cluttering up this namespace
(defn update-scroll-position [val scroll]
  "Updates the store with our current scroll position to re-position back on home view"
  (if scroll
      (.scrollTo js/window 0 (:scrollOffset @app-state)))
  (swap! app-state conj {:scrollOffset val}))

(defn handle-scroll-func [payload]
  "adds class to body preventing weird scroll on fixed position over scrolling main window"
  (if (= payload "")
    (do
      (.remove (.-classList (.-body js/document)) "hide-scroll")
      (update-scroll-position 0 true)) ; this should be instant
    (do
      (update-scroll-position (.-pageYOffset js/window) false)
      (js/setTimeout #(.add (.-classList (.-body js/document)) "hide-scroll") 100))))

(defn hide-tutorial [app-state payload]
  (swap! app-state conj {:show-tutorial payload}))

(defn update-active-view [app-state payload]
  (swap! app-state conj {:active-page {(keyword payload) "active"}})
  (handle-scroll-func payload))

(defn update-home-view [app-state payload]
  (swap! app-state conj {:home-view-active {(keyword payload) "active"}})
  (handle-scroll-func payload))

(defn update-flow-view [app-state payload]
  (swap! app-state conj {:flow-view-active {(keyword payload) "active"}})
  (handle-scroll-func payload))

(defn update-stats-view [app-state payload]
  (swap! app-state conj {:stats-view-active {(keyword payload) "active"}}))


(defn update-individual-entry [app-state payload]
  (swap! app-state conj {:individual-entry payload}))
