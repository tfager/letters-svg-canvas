(ns tfager.letters-svg-canvas
  (:require [dali.io :as io]
            [dali.layout.stack :as stack]
   ))

#_(def document
  [:dali/page {:width 260 :height 160}
   [:dali/stack
    {:position [10 10], :direction :right, :anchor :bottom-left, :gap 2}
    (map (fn [letter]
           [:text {:font-family "Impact" :font-size 26 :stroke :black :fill :none} letter]
           ["A" "B" "C" "Å" "Ä" "Ö"]
           ))
    ]])

(def document
  [:dali/page {:width 260 :height 160}
   [:dali/stack
    {:position [10 10], :direction :right, :anchor :bottom-left, :gap 2}
     [:text {:font-family "Impact" :font-size 26 :stroke :black :fill :none} "A"]
     [:text {:font-family "Impact" :font-size 26 :stroke :black :fill :none} "B"]
    ]])

(defn -main [& args]
  (io/render-svg document "hello-world.svg"))

