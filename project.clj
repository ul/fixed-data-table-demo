(defproject fixed-data-table-demo "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :url "http://example.com/FIXME"
            :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                           [org.clojure/clojurescript "0.0-3126"]
                           [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                           [org.omcljs/om "0.8.8"]
                           [cljsjs/fixed-data-table "0.1.2-0"]]
            :plugins [[lein-cljsbuild "1.0.5"]]
            :source-paths ["src" "target/classes"]
            :clean-targets ["out/fixed_data_table_demo" "out/fixed_data_table_demo.js"]
            :cljsbuild {:builds [{:id           "dev"
                                  :source-paths ["src"]
                                  :compiler     {:main           fixed-data-table-demo.core
                                                 :output-to      "out/fixed_data_table_demo.js"
                                                 :output-dir     "out"
                                                 :optimizations  :none
                                                 :cache-analysis true
                                                 :source-map     true}}
                                 {:id           "prod"
                                  :source-paths ["src"]
                                  :compiler     {:output-to     "out/fixed_data_table_demo.js"
                                                 :optimizations :advanced}}]})
