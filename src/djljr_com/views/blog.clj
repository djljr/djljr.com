(ns djljr-com.views.blog
  (:require [hiccup.page :refer [html5]]
            [djljr-com.views.common :as common]))


(defn render-post [{metadata :meta
                    {:keys [content date-published] :as entry} :entry}]
  (common/page
   [:div
    [:div#content
     [:div#post-meta
      (common/date->long-str date-published)]
     content]]))

(defn render-post-card [{:keys [name date-published description permalink]
                         :as post}]
  [:div
   [:div.post-header
    [:h2 [:a {:href permalink} name]]
    [:div#post-meta
     (common/date->date-str date-published)]]
   [:div.post-description description]])

(defn render-list [{metadata :meta posts :entries}]
  (common/page
   [:div#post-list
    (for [post (reverse (sort-by :date-published posts))]
      (render-post-card post))]))
