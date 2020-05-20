(ns mindfulness.services.persistence.storage
  (:require [mindfulness.services.scripts.dates :refer [format-date-object]]
            [nano-id.core :refer [nano-id]]
            [mindfulness.services.state.global :refer [app-state]]
            [mindfulness.services.state.dispatcher :refer [handle-state-change]]))

(def query-type "twc-mindfullness-entries")

(defn add-meta-data [current-object]
  (conj current-object {:date (format-date-object (js/Date.)) :id (nano-id 15)}))

(defn get-all-enteries []
  (.getItem (.-localforage js/window) query-type))

(defn save-day-entry [entry]
  (.then (get-all-enteries) (fn [enteries]
    (let [currentStorage (js->clj enteries :keywordize-keys true)]
       (.then (.setItem (.-localforage js/window) query-type (clj->js (conj currentStorage (add-meta-data entry)))
        (fn [value]
          (.then (get-all-enteries) (fn [enteries]
            (swap! app-state conj {:enteries (js->clj enteries :keywordize-keys true)})))
          (handle-state-change {:type "update-active-view" :value "complete"}))))))))
