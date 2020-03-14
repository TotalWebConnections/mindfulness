(ns mindfulness.services.persistence.storage
  (:require [mindfulness.services.scripts.dates :refer [format-date-object]]
            [nano-id.core :refer [nano-id]]))

(def query-type "twc-mindfullness-entries")

(defn add-meta-data [current-object]
  (conj current-object {:date (format-date-object (js/Date.)) :id (nano-id 15)}))

(defn get-all-enteries []
  (.getItem (.-localforage js/window) query-type))

(defn save-day-entry [entry]
  (.then (get-all-enteries) (fn [enteries]
    (let [currentStorage (js->clj enteries :keywordize-keys true)]
       (.then (.setItem (.-localforage js/window) query-type (clj->js (conj currentStorage (add-meta-data entry)))
        (fn [value])))))))
