(ns fixed-data-table-demo.core
  (:require [om.core :as om]
            [cljsjs.fixed-data-table]))

(enable-console-print!)

;;; calling a React component directly is deprecated since v0.12
;;; http://fb.me/react-legacyfactory
(def Table (js/React.createFactory js/FixedDataTable.Table))
(def Column (js/React.createFactory js/FixedDataTable.Column))
(def ColumnGroup (js/React.createFactory js/FixedDataTable.ColumnGroup))

(defn gen-table
  "Generate `size` rows vector of 4 columns vectors to mock up the table."
  [size]
  (mapv (fn [i] [i                                                   ; Number
                 (rand-int 1000)                                     ; Amount
                 (rand)                                              ; Coeff
                 (rand-nth ["Here" "There" "Nowhere" "Somewhere"])]) ; Store
        (range 1 (inc size))))

(def app-state (atom {:table (gen-table 1000)}))

;;; using custom :cellDataGetter in column for cljs persistent data structure
;;; is more efficient than converting row to js array in table's :rowGetter
(defn getter [k row] (get row k))

(defn App [{:keys [table]} _]
  (reify om/IRender
    (render [_]
      (Table
        #js {:width        600
             :height       400
             :rowHeight    30
             :rowGetter    #(get table %)
             :rowsCount    (count table)
             :headerHeight 30}
        (Column
          #js {:label "Number" :dataKey 0 :cellDataGetter getter :width 100})
        (Column
          #js {:label "Amount" :dataKey 1 :cellDataGetter getter :width 100})
        (Column
          #js {:label "Coeff" :dataKey 2 :cellDataGetter getter :width 200})
        (Column
          #js {:label "Store" :dataKey 3 :cellDataGetter getter :width 200})))))

(om/root App app-state {:target (. js/document (getElementById "app"))})
