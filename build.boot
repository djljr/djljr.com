(set-env!
 :source-paths #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [perun "0.4.2-SNAPSHOT" :scope "test"]
                 [hashobject/boot-s3 "0.1.2-SNAPSHOT"]
                 [pandeiro/boot-http "0.7.0"]
                 [org.martinklepsch/boot-gzip "0.1.2"]
                 [clj-time "0.13.0"]])

(require '[io.perun :refer :all]
         '[djljr-com.views.index :as index-view]
         '[djljr-com.views.about :as about-view]
         '[pandeiro.boot-http :refer [serve]]
         '[hashobject.boot-s3 :refer :all]
         '[org.martinklepsch.boot-gzip :refer [gzip]])

(task-options!
 pom {:project 'djljr.com
      :version "1.0.0"}
 s3-sync {:bucket "djljr.com"
          :access-key (System/getenv "AWS_ACCESS_KEY")
          :secret-key (System/getenv "AWS_SECRET_KEY")
          :source "public"
          :options {"Cache-Control" "max-age=315360000, no-transform, public"}})

(deftask build-dev
  "Develop site"
  []
  (comp (global-metadata)
        (markdown)
        (draft)
        (print-meta)
        (render :renderer 'djljr-com.views.index/render :filterer #(= :post (:type %)))
        (render :renderer 'djljr-com.views.blog/render-post :filterer #(= :post (:type %)))
        (collection :renderer 'djljr-com.views.blog/render-list :page "blog/index.html")))

(deftask build
  "Build site for deployment"
  []
  (comp (build-dev)
        (inject-scripts :scripts #{"ga.js"})
        (sitemap :filename "sitemap.xml")
        (rss)
        (gzip :regex [#".html$" #".css$" #".js$"])
        (s3-sync)))

(deftask dev
  "Serve dev site"
  []
  (comp (watch)
        (build-dev)
        (serve :resource-root "public" :port 4000)))
