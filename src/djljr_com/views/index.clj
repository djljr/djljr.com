(ns djljr-com.views.index
  (:require [djljr-com.views.common :as common]))

(defn render [{metadata :meta page :entry}]
  (common/page
   (:content page)))
