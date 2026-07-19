(ns com.tmt.ui.icons)

(defn arrow-icon []
  [:svg {:viewBox "0 0 20 20"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 transition-transform duration-200 group-hover:translate-x-0.5"
         :style {:display "inline" :fill "none" :stroke "currentcolor" :stroke-width "2"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M4.5 10h11m0 0-4-4m4 4-4 4"}]])

(defn check-icon []
  [:svg {:viewBox "0 0 20 20"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0 text-brand-600"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "2"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M4 10.5 8 14.5 16 5.5"}]])

(defn clock-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M12 7v5l3.5 2M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"}]])

(defn shield-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-6 text-brand-600"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M12 3 4.5 6v6c0 4.5 3.2 7.9 7.5 9 4.3-1.1 7.5-4.5 7.5-9V6L12 3Z"}]])

(defn pin-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-5 shrink-0 text-brand-600"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M12 21s-7-6.1-7-11.5A7 7 0 0 1 19 9.5C19 14.9 12 21 12 21Z"}]
   [:circle {:cx "12" :cy "9.5" :r "2.25"}]])

(defn expand-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-6 text-white"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M9 3H4v5M15 21h5v-5M20 3l-6 6M4 21l6-6"}]])

(defn bed-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0 text-gray-400"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M3 18v-6a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v6M3 18v2M21 18v2M3 12V7a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v3M13 9h6a2 2 0 0 1 2 2v1"}]])

(defn users-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0 text-gray-400"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M17 20v-1a4 4 0 0 0-4-4H7a4 4 0 0 0-4 4v1M15 11a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM21 20v-1a4 4 0 0 0-3-3.87M15.5 5.13A3 3 0 0 1 18 8a3 3 0 0 1-1.28 2.46"}]])

(defn phone-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :style {:display "block" :height "16px" :width "16px" :fill "currentcolor"}}
   [:path {:d "M6.62 10.79a15.05 15.05 0 006.59 6.59l2.2-2.2a1 1 0 011.01-.24c1.12.37 2.33.57 3.58.57a1 1 0 011 1v3.5a1 1 0 01-1 1c-9.39 0-17-7.61-17-17a1 1 0 011-1h3.5a1 1 0 011 1c0 1.25.2 2.46.57 3.58a1 1 0 01-.25 1.01z"}]])

(defn menu-icon []
  [:svg {:viewBox "0 0 32 32"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :role "presentation"
         :focusable "false"
         :style {:display "block" :fill "none" :height "20px" :width "20px"
                 :stroke "currentcolor" :stroke-width "3" :overflow "visible"}}
   [:g {:fill "none" :fill-rule "nonzero"}
    [:path {:d "m2 16h28"}]
    [:path {:d "m2 24h28"}]
    [:path {:d "m2 8h28"}]]])
