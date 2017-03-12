(ns djljr-com.views.index
  (:require [djljr-com.views.common :as common]))

(defn render [{metadata :meta page :entry}]
  (println "index " metadata (dissoc page :content))
  (common/page
   (:content page)))
