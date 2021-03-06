(defproject norman_sicily_static "0.1.0-SNAPSHOT"
  :description "The Norman Sicily Project"
  :url "http://www.normansicily.org"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [stasis "2.5.0"]
                 [ring "1.8.0"]
                 [hiccup "1.0.5"]
                 [enlive "1.1.6"]
                 [optimus "0.20.2"]
                 [optimus-less "0.2.1"]
                 [cheshire "5.9.0"]]
  :plugins [[lein-ftp-static-deploy "0.1.0"]]
  :ring {:handler norman-sicily-static.web/app
         :auto-refresh? true}
  :aliases {"build-site" ["run" "-m" "norman-sicily-static.web/export"]
            "autotest" ["with-profile" "test" "midje" ":autotest"]
            "serve" ["ring" "server"]}
  :clean-targets [:target-path "dist"]
  :profiles {:dev {:plugins [[lein-ring "0.12.5"]]}
             :test {:dependencies [[midje "1.9.9"]]
                    :plugins [[lein-midje "3.2.1"]]}}
  :ftp {:host ~(System/getenv "FTP_DEPLOY_HOST")
        :user ~(System/getenv "FTP_DEPLOY_USER")
        :pass ~(System/getenv "FTP_DEPLOY_PASS")
        :port ~(or (System/getenv "FTP_DEPLOY_PORT") 21)
        :ftp-static-deploy {
                            :remote-root "/public_html/norman_sicily_static"
                            :local-root "dist"}})
