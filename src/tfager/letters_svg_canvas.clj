(ns tfager.letters-svg-canvas
  (:require [dali.io :as io]
            [dali.layout.stack :as stack]
            [dali.layout.align :as align]
   ))

(def font-name "Zurich BlkEx BT, Expanded")

(def letters (vec "ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ01234567890.!,2222"))

(defn boxed-letter [letter-str]
  [:dali/align {:axis :center}
    [:rect {:fill :none :stroke :black :stroke-width 0.01} :_ [32 32]]
    [:text {:font-family font-name :font-size 26 :stroke :black :stroke-width 0.01 :fill :none} letter-str]])

(def document
  [:dali/page
   (vec (concat
     [:dali/stack
      {:position [10 10], :direction :right, :anchor :bottom-left, :gap 5}]
      (vec (map (comp boxed-letter str) letters))))
     ])

(defn -main [& args]
  ;; TODO: Box width according to letter width
  ;; TODO: Align to baseline, not center (cf. A and Ä)
  (io/render-svg document "hello-world.svg"))

