(ns djljr-com.views.blog
  (:require [hiccup.page :refer [html5]]
            [djljr-com.views.common :as common]))


(defn render-post [{metadata :meta
                    {:keys [content date-created] :as entry} :entry}]
  (common/page
   [:div
    [:div#content
     [:div#post-meta
      (common/date->time-ago date-created)]
     content]]))

(defn render-post-card [{:keys [name date-created description permalink]
                         :as post}]
  [:div
   [:div.post-header
    [:h2 [:a {:href permalink} name]]
    [:div#post-meta
     (common/date->date-str date-created)]]
   [:div.post-description description]])

(defn render-list [{metadata :meta posts :entries}]
  (common/page
   [:div#post-list
    (for [post posts]
      (render-post-card post))]))
