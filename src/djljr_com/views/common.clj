(ns djljr-com.views.common
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css include-js html5]]
            [clj-time.coerce :as tc]
            [clj-time.format :as tf]
            [clj-time.core :as t]
            [clojure.contrib.humanize :as hu]))

(defn date->time-ago [date]
  (hu/datetime date))

(defn date->date-str [date]
  (tf/unparse (tf/formatters :date) (tc/from-date date)))

(defn header []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
   [:meta {:itemprop "author" :name "author" :content "Dennis Lipovsky (dennis.lipovsky@gmail.com)"}]
   [:meta {:itemprop "keywords" :name "keywords" :content "djljr, blog, clojure, 3d printing, functional programming, maker"}]
   [:meta {:itemprop "description" :name "description" :content "blog / dennis lipovsky"}]
   [:title "Blog / Dennis Lipovsky"]
   [:link {:rel "shortcut icon" :href "/favicon.ico"}]
   [:link {:rel "publisher" :href "https://plus.google.com/110997094162118423831"}]
   [:link {:rel "author" :href "humans.txt"}]
   [:link {:rel "alternate" :type "application/rss+xml" :title "RSS" :href "/feed.rss"}]
   (include-css "http://fonts.googleapis.com/css?family=Alegreya:400italic,700italic,400,700")
   (include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css")
   (include-css "//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css")
   (include-css "//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.7.0/styles/default.min.css")
   (include-css "/css/app.css")])

(defn nav []
  [:nav.navbar.navbar-default
   [:div.container
    [:div.navbar-header
     [:button.navbar-toggle.collapsed {:type "button" :data-toggle "collapse" :data-target "#navbar" :aria-expanded "false" :aria-controls "navbar"}
      [:span.sr-only "Toggle navigation"]
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]
     [:a.navbar-brand {:href "/"} "dennis lipovsky"]]
    [:div#navbar.navbar-collapse.collapse
     [:ul.nav.navbar-nav.navbar-right
      [:li [:a {:href "/blog"} "Blog"]]
      [:li [:a {:href "/about.html"} "About"]]]]]])

(defn footer []
  [:footer (str "Copyright &copy; " (t/year (t/now)) " Dennis Lipovsky")
   [:div.social
    [:a {:href "https://github.com/djljr"} "GitHub"] " | "
    [:a {:href "https://twitter.com/djljr"} "Twitter"] " | "
    [:a {:href "https://instagram.com/dlipovsky"} "Instagram"]]])

(defn scripts []
  [:div
   (include-js "//code.jquery.com/jquery-1.11.0.min.js")
   (include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js")
   (include-js "/js/highlight.pack.js")
   [:script "hljs.initHighlightingOnLoad();"]])

(defn page [& content]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         (header)
         [:body
          (nav)
          [:div.container
           [:div.row
            [:div.col-lg-12
             [:div.content
              content]]]]
          (footer)
          (scripts)]))
