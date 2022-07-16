(ns tfager.letters-svg-canvas
  (:require [dali.io :as io]
            [dali.layout.stack]
            [dali.layout.align]
            [dali.layout.surround]
   ))

(def font-name "Zurich BlkEx BT, Expanded")
(def stroke-width 0.1)

(def letters (vec "ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ01234567890.!,2222"))

(def next-id (atom 1))

(defn boxed-letter [letter-str]
  (let [id-tag (str "letter_" (swap! next-id inc))]
    [:dali/align {:axis :center}
      [:text {:id id-tag
              :font-family font-name
              :font-size 26
              :stroke :black
              :stroke-width stroke-width
              :fill :none}
       letter-str]
      [:dali/surround {:select [(keyword (str "#" id-tag))]
                       :padding 2
                       :attrs {:fill :none
                               :stroke :black
                               :stroke-width stroke-width
                               :height 32}}]]))

(def document
  [:dali/page
   (vec (concat
     [:dali/stack
      {:position [10 10], :direction :right, :anchor :bottom-left, :gap 2}]
      (vec (map (comp boxed-letter str) letters))))
     ])

; Test wide, narrow and high letters
(def testdoc
  [:dali/page
   (vec (concat
          [:dali/stack
           {:position [10 10], :direction :right, :anchor :bottom-left, :gap 0}]
          (vec (map (comp boxed-letter str) "AIMÖ"))))])

(defn -main [& args]
  ;; TODO: Align to baseline, not center (cf. A and Ä)
  (if (< (count args) 1)
    (throw (Exception. "Output SVG file name required as first argument")))
  (io/render-svg testdoc (first args)))

