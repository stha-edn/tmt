(ns com.tmt.ui.illustrations)

(defn house-illustration []
  [:svg {:viewBox "0 0 800 600" :xmlns "http://www.w3.org/2000/svg"
         :class "w-full h-auto" :style {:display "block"}}
   [:defs
    [:linearGradient {:id "hi-roof" :x1 "0" :y1 "0" :x2 "0" :y2 "1"}
     [:stop {:offset "0%" :stop-color "#736150"}]
     [:stop {:offset "100%" :stop-color "#5c4d3f"}]]
    [:linearGradient {:id "hi-body" :x1 "0" :y1 "0" :x2 "0" :y2 "1"}
     [:stop {:offset "0%" :stop-color "#faf8f5"}]
     [:stop {:offset "100%" :stop-color "#f0ece6"}]]]
   [:g {:transform "translate(400,300)"}
    [:rect {:x "-120" :y "-80" :width "240" :height "160" :rx "16" :fill "url(#hi-body)" :stroke "#e0d8ce" :stroke-width "3"}]
    [:polygon {:points "-140,-80 0,-160 140,-80" :fill "url(#hi-roof)" :stroke "#5c4d3f" :stroke-width "3" :stroke-linejoin "round"}]
    [:rect {:x "-30" :y "-20" :width "60" :height "100" :rx "8" :fill "#e0d8ce" :stroke "#ad9d8c" :stroke-width "2.5"}]
    [:circle {:cx "0" :cy "30" :r "6" :fill "#ad9d8c"}]
    [:rect {:x "-80" :y "-10" :width "35" :height "50" :rx "6" :fill "#f0ece6" :stroke "#c9bdb0" :stroke-width "2"}]
    [:rect {:x "45" :y "-10" :width "35" :height "50" :rx "6" :fill "#f0ece6" :stroke "#c9bdb0" :stroke-width "2"}]
    [:rect {:x "-120" :y "80" :width "240" :height "12" :rx "4" :fill "#e0d8ce"}]
    [:circle {:cx "-80" :cy "80" :r "20" :fill "#c9bdb0" :opacity "0.3"}]
    [:circle {:cx "60" :cy "80" :r "16" :fill "#c9bdb0" :opacity "0.2"}]
    [:rect {:x "-180" :y "120" :width "360" :height "60" :rx "12" :fill "#f0ece6" :stroke "#e0d8ce" :stroke-width "2.5"}]]])

(defn travel-illustration []
  [:svg {:viewBox "0 0 800 600" :xmlns "http://www.w3.org/2000/svg"
         :class "w-full h-auto" :style {:display "block"}}
   [:defs
    [:linearGradient {:id "ti-mountain" :x1 "0" :y1 "0" :x2 "0" :y2 "1"}
     [:stop {:offset "0%" :stop-color "#ad9d8c"}]
     [:stop {:offset "100%" :stop-color "#907d6a"}]]]
   [:g {:transform "translate(400,320)"}
    [:polygon {:points "-300,100 -200,-80 -100,100" :fill "url(#ti-mountain)" :opacity "0.3"}]
    [:polygon {:points "-100,100 50,-120 200,100" :fill "url(#ti-mountain)" :opacity "0.5"}]
    [:polygon {:points "100,100 250,-60 400,100" :fill "url(#ti-mountain)" :opacity "0.3"}]
    [:circle {:cx "-120" :cy "-140" :r "40" :fill "#c9bdb0" :opacity "0.2"}]
    [:rect {:x "-80" :y "-20" :width "160" :height "80" :rx "16" :fill "#faf8f5" :stroke "#e0d8ce" :stroke-width "3"}]
    [:rect {:x "-60" :y "-40" :width "120" :height "24" :rx "6" :fill "#f0ece6" :stroke "#c9bdb0" :stroke-width "2.5"}]
    [:circle {:cx "0" :cy "-28" :r "6" :fill "#ad9d8c"}]
    [:rect {:x "-70" :y "0" :width "30" :height "20" :rx "4" :fill "#e0d8ce"}]
    [:rect {:x "40" :y "0" :width "30" :height "20" :rx "4" :fill "#e0d8ce"}]
    [:circle {:cx "0" :cy "70" :r "30" :fill "#c9bdb0" :opacity "0.25"}]
    [:circle {:cx "-30" :cy "65" :r "8" :fill "#e0d8ce"}]
    [:circle {:cx "20" :cy "75" :r "6" :fill "#e0d8ce"}]
    [:rect {:x "-160" :y "100" :width "320" :height "6" :rx "3" :fill "#e0d8ce" :opacity "0.5"}]
    [:rect {:x "-100" :y "120" :width "200" :height "40" :rx "20" :fill "#f0ece6" :stroke "#c9bdb0" :stroke-width "2.5"}]]])

(defn nature-illustration []
  [:svg {:viewBox "0 0 800 600" :xmlns "http://www.w3.org/2000/svg"
         :class "w-full h-auto" :style {:display "block"}}
   [:defs
    [:linearGradient {:id "ni-tree" :x1 "0" :y1 "0" :x2 "0" :y2 "1"}
     [:stop {:offset "0%" :stop-color "#ad9d8c"}]
     [:stop {:offset "100%" :stop-color "#736150"}]]]
   [:g {:transform "translate(400,300)"}
    [:circle {:cx "0" :cy "-100" :r "80" :fill "#c9bdb0" :opacity "0.4"}]
    [:circle {:cx "-60" :cy "-130" :r "50" :fill "#c9bdb0" :opacity "0.2"}]
    [:circle {:cx "50" :cy "-120" :r "40" :fill "#c9bdb0" :opacity "0.25"}]
    [:rect {:x "-20" :y "-20" :width "40" :height "80" :rx "8" :fill "#e0d8ce" :stroke "#c9bdb0" :stroke-width "2.5"}]
    [:polygon {:points "-100,-20 0,-120 100,-20" :fill "url(#ni-tree)" :stroke "#5c4d3f" :stroke-width "2.5" :stroke-linejoin "round"}]
    [:ellipse {:cx "-80" :cy "80" :rx "140" :ry "40" :fill "#f0ece6" :stroke "#e0d8ce" :stroke-width "3"}]
    [:ellipse {:cx "60" :cy "100" :rx "100" :ry "30" :fill "#f0ece6" :stroke "#e0d8ce" :stroke-width "2.5"}]
    [:circle {:cx "-60" :cy "50" :r "6" :fill "#c9bdb0" :opacity "0.5"}]
    [:circle {:cx "-40" :cy "60" :r "4" :fill "#c9bdb0" :opacity "0.4"}]
    [:circle {:cx "30" :cy "55" :r "5" :fill "#c9bdb0" :opacity "0.45"}]
    [:rect {:x "-120" :y "120" :width "240" :height "8" :rx "4" :fill "#e0d8ce" :opacity "0.4"}]]])

(defn welcome-illustration []
  [:svg {:viewBox "0 0 800 600" :xmlns "http://www.w3.org/2000/svg"
         :class "w-full h-auto" :style {:display "block"}}
   [:defs
    [:linearGradient {:id "wi-door" :x1 "0" :y1 "0" :x2 "0" :y2 "1"}
     [:stop {:offset "0%" :stop-color "#ad9d8c"}]
     [:stop {:offset "100%" :stop-color "#736150"}]]]
   [:g {:transform "translate(400,300)"}
    [:rect {:x "-140" :y "-100" :width "280" :height "200" :rx "20" :fill "#faf8f5" :stroke "#e0d8ce" :stroke-width "3"}]
    [:polygon {:points "-160,-100 0,-200 160,-100" :fill "#907d6a" :stroke "#5c4d3f" :stroke-width "3" :stroke-linejoin "round"}]
    [:rect {:x "-50" :y "-40" :width "100" :height "130" :rx "10" :fill "url(#wi-door)" :stroke "#5c4d3f" :stroke-width "2.5"}]
    [:circle {:cx "35" :cy "30" :r "5" :fill "#e0d8ce"}]
    [:rect {:x "-100" :y "-40" :width "40" :height "60" :rx "8" :fill "#f0ece6" :stroke "#c9bdb0" :stroke-width "2"}]
    [:rect {:x "60" :y "-40" :width "40" :height "60" :rx "8" :fill "#f0ece6" :stroke "#c9bdb0" :stroke-width "2"}]
    [:rect {:x "-60" :y "-80" :width "20" :height "20" :rx "4" :fill "#e0d8ce"}]
    [:rect {:x "40" :y "-80" :width "20" :height "20" :rx "4" :fill "#e0d8ce"}]
    [:ellipse {:cx "0" :cy "120" :rx "200" :ry "30" :fill "#f0ece6" :stroke "#e0d8ce" :stroke-width "2.5"}]
    [:circle {:cx "-120" :cy "130" :r "25" :fill "#c9bdb0" :opacity "0.2"}]
    [:circle {:cx "100" :cy "120" :r "20" :fill "#c9bdb0" :opacity "0.15"}]
    [:circle {:cx "-160" :cy "140" :r "15" :fill "#c9bdb0" :opacity "0.1"}]]])

(defn map-illustration []
  [:svg {:viewBox "0 0 800 600" :xmlns "http://www.w3.org/2000/svg"
         :class "w-full h-auto" :style {:display "block"}}
   [:defs
    [:linearGradient {:id "mi-pin" :x1 "0" :y1 "0" :x2 "0" :y2 "1"}
     [:stop {:offset "0%" :stop-color "#907d6a"}]
     [:stop {:offset "100%" :stop-color "#5c4d3f"}]]]
   [:g {:transform "translate(400,300)"}
    [:rect {:x "-180" :y "-140" :width "360" :height "280" :rx "16" :fill "#faf8f5" :stroke "#e0d8ce" :stroke-width "3"}]
    [:rect {:x "-160" :y "-120" :width "320" :height "240" :rx "8" :fill "#f0ece6"}]
    [:path {:d "M-140,-80 Q-80,-60 -40,-100 Q0,-140 60,-80 Q120,-20 140,-60" :fill "none" :stroke "#c9bdb0" :stroke-width "3" :stroke-linecap "round"}]
    [:path {:d "M-120,20 Q-40,60 20,0 Q80,-40 140,20" :fill "none" :stroke "#c9bdb0" :stroke-width "3" :stroke-linecap "round"}]
    [:path {:d "M-100,80 Q-20,100 40,60 Q100,20 140,80" :fill "none" :stroke "#c9bdb0" :stroke-width "3" :stroke-linecap "round"}]
    [:circle {:cx "0" :cy "20" :r "8" :fill "#ad9d8c"}]
    [:polygon {:points "0,12 -8,28 0,24 8,28" :fill "#5c4d3f"}]
    [:circle {:cx "-80" :cy "-60" :r "5" :fill "#ad9d8c"}]
    [:circle {:cx "80" :cy "-40" :r "5" :fill "#ad9d8c"}]
    [:circle {:cx "60" :cy "70" :r "5" :fill "#ad9d8c"}]
    [:rect {:x "-10" :y "30" :width "20" :height "8" :rx "2" :fill "#e0d8ce"}]
    [:circle {:cx "0" :cy "100" :r "24" :fill "#c9bdb0" :opacity "0.2"}]]])
