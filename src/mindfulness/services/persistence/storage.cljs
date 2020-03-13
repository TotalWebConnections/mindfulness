(ns mindfulness.services.persistence.storage)

(def query-type "twc-mindfullness-entries")

(defn get-all-enteries []
  (.getItem (.-localforage js/window) query-type))

(defn save-day-entry [entry]
  (.then (get-all-enteries) (fn [enteries]
    (let [currentStorage (js->clj enteries :keywordize-keys true)]
       (.then (.setItem (.-localforage js/window) query-type (clj->js (conj currentStorage entry))
        (fn [value])))))))
