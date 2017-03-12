(ns djljr-com.views.blog
  (:require [hiccup.page :refer [html5]]
            [djljr-com.views.common :as common]))


(defn render-post [{metadata :meta post :entry}]
  (println "render-post" metadata (keys post))
  (common/page
   [:div "Blog Post"]))

(defn render-list [{metadata :meta posts :entries}]
  (println "render-list" metadata (count posts))
  (common/page
   [:div "Blog Post List"]))
