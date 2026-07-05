(ns com.tmt.ui.components.nav)

(def ^:private nav-links
  [["Home" "/"]
   ["Rooms" "/rooms"]
   ["Tours" "/tours"]
   ["Gallery" "/gallery"]
   ["About" "/about"]
   ["Contact" "/contact"]])

(defn- phone-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :style {:display "block" :height "16px" :width "16px" :fill "currentcolor"}}
   [:path {:d "M6.62 10.79a15.05 15.05 0 006.59 6.59l2.2-2.2a1 1 0 011.01-.24c1.12.37 2.33.57 3.58.57a1 1 0 011 1v3.5a1 1 0 01-1 1c-9.39 0-17-7.61-17-17a1 1 0 011-1h3.5a1 1 0 011 1c0 1.25.2 2.46.57 3.58a1 1 0 01-.25 1.01z"}]])

(defn- arrow-icon []
  [:svg {:viewBox "0 0 20 20"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 transition-transform duration-200 group-hover:translate-x-0.5"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "2"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M4.5 10h11m0 0-4-4m4 4-4 4"}]])

(defn- menu-icon []
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

(defn- logo []
  [:div.inline-flex
   [:a {:class "flex items-center gap-2" :href "/"}
    [:span {:class "text-xl font-bold tracking-tight text-blue-700"} "TM"]
    [:span {:class "hidden sm:block text-xl font-semibold text-gray-800"} "Guest Lodge"]]])

(defn- desktop-links []
  [:div {:class "hidden md:flex items-center gap-6"}
   (for [[label href] nav-links]
     [:a {:href href
          :class "text-sm font-medium text-gray-700 hover:text-blue-700"}
      label])])

(defn- phone-numbers []
  [:div {:class "hidden md:flex items-center gap-2 text-sm text-gray-700"}
   (phone-icon)
   [:span "(033) 386 9139 / (033) 346 0177"]])

(defn- mobile-menu-button []
  [:button {:type "button"
            :class "md:hidden inline-flex items-center justify-center p-2 border rounded-full hover:bg-gray-100"
            :_ "on click toggle .hidden on #mobile-nav-menu"}
   (menu-icon)])

(defn- mobile-menu []
  [:div#mobile-nav-menu {:class "hidden md:hidden absolute top-20 left-0 w-full bg-white border-t shadow-lg z-10"}
   [:div {:class "flex flex-col px-8 py-4 gap-3"}
    (for [[label href] nav-links]
      [:a {:href href
           :class "text-sm font-medium text-gray-700 hover:text-blue-700"}
       label])
    [:div {:class "flex items-center gap-2 text-sm text-gray-700 pt-2 border-t"}
     (phone-icon)
     [:span "(033) 386 9139 / (033) 346 0177"]]]])

(defn navbar []
  [:.relative
   [:nav {:class "bg-white w-full flex relative justify-between items-center mx-auto px-8 h-20"}
    (logo)
    (desktop-links)
    [:div {:class "flex items-center gap-4"}
     (phone-numbers)
     [:a {:href "/reservations"
          :class (str "group hidden sm:inline-flex items-center gap-1.5 rounded-full bg-blue-600 "
                      "px-5 py-2.5 text-sm font-semibold text-white shadow-sm "
                      "transition-colors duration-200 hover:bg-blue-500 active:bg-blue-700 "
                      "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-blue-500 "
                      "focus-visible:ring-offset-2")}
      "Book Now"
      (arrow-icon)]
     (mobile-menu-button)]]
   (mobile-menu)])
