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
         :class "size-4 shrink-0"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M3 18v-6a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v6M3 18v2M21 18v2M3 12V7a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v3M13 9h6a2 2 0 0 1 2 2v1"}]])

(defn users-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0"
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

(defn house-icon []
  [:svg {:viewBox "0 0 24 24" :fill "none" :class "size-5"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}}
   [:path {:d "M3 12L5 10.5M5 10.5L12 5L19 10.5M5 10.5V20C5 20.5523 5.44772 21 6 21H9M19 10.5L21 12M19 10.5V20C19 20.5523 18.5523 21 18 21H15M9 21C9.55228 21 10 20.5523 10 20V16C10 15.4477 10.4477 15 11 15H13C13.5523 15 14 15.4477 14 16V20C14 20.5523 14.4477 21 15 21M9 21H15"}]])

(defn mail-icon []
  [:svg {:viewBox "0 0 24 24" :fill "none" :class "size-4 shrink-0"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}}
   [:path {:d "M21.75 6.75L12 13.25L2.25 6.75"}]
   [:path {:d "M3 5.25H21C21.4142 5.25 21.75 5.58579 21.75 6V18C21.75 18.4142 21.4142 18.75 21 18.75H3C2.58579 18.75 2.25 18.4142 2.25 18V6C2.25 5.58579 2.58579 5.25 3 5.25Z"}]])

(defn chevron-up-icon []
  [:svg {:viewBox "0 0 24 24" :fill "none" :class "size-3"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}}
   [:path {:d "M5 15L12 8L19 15"}]])

(defn x-icon []
  [:svg {:viewBox "0 0 24 24" :fill "none" :class "size-5"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}}
   [:path {:d "M6 6L18 18"}]
   [:path {:d "M18 6L6 18"}]])

(defn facebook-icon []
  [:svg {:viewBox "0 0 24 24" :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true" :focusable "false"
         :class "size-4" :style {:display "block" :fill "currentcolor"}}
   [:path {:d "M22 12a10 10 0 10-11.56 9.88v-6.99H7.9V12h2.54V9.8c0-2.5 1.5-3.89 3.78-3.89 1.1 0 2.24.2 2.24.2v2.46h-1.26c-1.24 0-1.63.77-1.63 1.56V12h2.78l-.44 2.89h-2.34v6.99A10 10 0 0022 12z"}]])

(defn twitter-icon []
  [:svg {:viewBox "0 0 24 24" :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true" :focusable "false"
         :class "size-4" :style {:display "block" :fill "currentcolor"}}
   [:path {:d "M22 5.9c-.63.28-1.3.47-2 .56.72-.43 1.28-1.12 1.54-1.94-.67.4-1.42.68-2.22.84A3.48 3.48 0 0016.7 4c-1.94 0-3.5 1.57-3.5 3.5 0 .27.03.54.09.79A9.9 9.9 0 013 4.9a3.5 3.5 0 001.08 4.67c-.57-.02-1.1-.17-1.57-.43v.04c0 1.7 1.2 3.13 2.8 3.45-.3.08-.6.12-.92.12-.22 0-.44-.02-.65-.06a3.5 3.5 0 003.27 2.44A7.02 7.02 0 012 16.56 9.9 9.9 0 007.29 18c6.34 0 9.8-5.25 9.8-9.8 0-.15 0-.3-.01-.44A7 7 0 0022 5.9z"}]])

(defn arrow-up-right-icon []
  [:svg {:viewBox "0 0 20 20" :fill "none" :class "size-3.5"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}}
   [:path {:d "M5 15L15 5"}]
   [:path {:d "M9 5H15V11"}]])

(defn menu-alt-icon []
  [:svg {:viewBox "0 0 24 24" :fill "none" :class "size-6"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round"}}
   [:path {:d "M4 6H20"}]
   [:path {:d "M4 12H20"}]
   [:path {:d "M4 18H20"}]])

(defn tag-icon []
  [:svg {:viewBox "0 0 24 24" :fill "none" :class "size-5"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}}
   [:path {:d "M9 5H5v4l10 10 4-4L9 5z"}]
   [:circle {:cx "7.5" :cy "7.5" :r "1.5" :fill "currentcolor"}]])

(defn star-icon []
  [:svg {:viewBox "0 0 24 24" :fill "none" :class "size-5"
         :style {:display "block" :stroke "currentcolor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}}
   [:path {:d "M12 2l3 6.5 7 .5-5.3 5 1.3 7-6-3.5L6 21l1.3-7L2 9l7-.5L12 2z"}]])

(defn whatsapp-icon []
  [:svg {:viewBox "0 0 24 24" :class "size-5"
         :style {:display "block" :fill "currentcolor"}}
   [:path {:d "M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"}]])
