(ns djljr-com.views.post
  (:require [hiccup.page :refer [html5]]
            [djljr-com.views.common :as common]))

(defn render [{metadata :meta}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         (common/header)
         [:body
          (common/nav)
          (common/footer)]))
