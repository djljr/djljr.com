(set-env!
 :source-paths #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [perun "0.4.2-SNAPSHOT" :scope "test"]
                 [confetti/confetti "0.1.5"]
                 [pandeiro/boot-http "0.7.0"]
                 [org.martinklepsch/boot-gzip "0.1.2"]
                 [clj-time "0.13.0"]
                 [clojure-humanize "0.2.2"]])

(require '[io.perun :refer :all]
         '[pandeiro.boot-http :refer [serve]]
         '[confetti.boot-confetti :refer [sync-bucket]]
         '[org.martinklepsch.boot-gzip :refer [gzip]])

(task-options!
 pom {:project 'djljr.com
      :version "1.0.0"}
 sync-bucket {:bucket "djljr.com"
              :access-key (System/getenv "AWS_ACCESS_KEY")
              :secret-key (System/getenv "AWS_SECRET_KEY")
              :dir "target/public"})

(defn post? [page]
  (= "post" (:type page)))

(deftask build
  "Develop site"
  []
  (comp (global-metadata)
        (markdown)
        (draft)
        (print-meta)
        (render :renderer 'djljr-com.views.index/render :filterer (comp not post?))
        (render :renderer 'djljr-com.views.blog/render-post :filterer post?)
        (collection :renderer 'djljr-com.views.blog/render-list
                    :page "blog/index.html"
                    :filterer post?)))

(deftask deploy
  "Build site for deployment"
  []
  (comp (build)
        (inject-scripts :scripts #{"ga.js"})
        (sitemap :filename "sitemap.xml")
        (rss)
        (gzip :regex [#".html$" #".css$" #".js$"])
        (target)
        (sync-bucket)))

(deftask dev
  "Serve dev site"
  []
  (comp (watch)
        (build)
        (serve :resource-root "public" :port 4000)))
