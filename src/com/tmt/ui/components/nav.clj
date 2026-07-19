(ns com.tmt.ui.components.nav
  (:require [com.tmt.ui.icons :as icons]))

(def ^:private nav-links
  [["Home" "/"]
   ["Rooms" "/rooms"]
   ["Tours" "/tours"]
   ["Gallery" "/gallery"]
   ["About" "/about"]
   ["Contact" "/contact"]])

(defn- logo []
  [:a {:class "flex items-center gap-2.5 shrink-0" :href "/"}
   [:div {:class "flex size-9 items-center justify-center rounded-xl bg-brand-600 text-white shadow-sm"}
    (icons/house-icon)]
   [:div
    [:span {:class "block text-base font-bold tracking-tight text-gray-900 leading-tight"} "TM"]
    [:span {:class "block text-xs font-medium text-gray-500 leading-tight"} "Guest Lodge"]]])

(defn- desktop-links [active-uri]
  [:div {:class "hidden md:flex items-center gap-1"}
   (for [[label href] nav-links]
     (let [active? (= active-uri href)]
       [:a {:href href
            :class (str "px-3 py-2 text-sm font-medium rounded-lg transition-colors duration-200 "
                        (if active?
                          "bg-brand-50 text-brand-700"
                          "text-gray-600 hover:text-brand-700 hover:bg-brand-50/50"))}
        label]))])

(defn- mobile-menu-button []
  [:button {:type "button"
            :class (str "md:hidden inline-flex items-center justify-center p-2.5 rounded-xl "
                        "text-gray-600 hover:text-brand-700 hover:bg-brand-50 "
                        "transition-colors duration-200")
            :_ "on click toggle .hidden on #mobile-nav-menu toggle .hidden on #mobile-overlay"}
   [:span {:class "sr-only"} "Open menu"]
   (icons/menu-alt-icon)])

(defn- mobile-menu [active-uri]
  [:div#mobile-overlay {:class "hidden md:hidden fixed inset-0 z-40 bg-black/40 backdrop-blur-sm"
                        :_ "on click toggle .hidden on #mobile-nav-menu toggle .hidden on me"}
   [:div#mobile-nav-menu {:class "hidden fixed inset-y-0 right-0 z-50 w-72 max-w-[85vw] bg-white shadow-2xl flex flex-col"}
    [:div {:class "flex items-center justify-between px-6 h-20 border-b border-gray-100"}
     [:a {:class "flex items-center gap-2.5" :href "/"}
      [:div {:class "flex size-9 items-center justify-center rounded-xl bg-brand-600 text-white shadow-sm"}
       (icons/house-icon)]
      [:span {:class "text-base font-bold text-gray-900"} "TM Guest Lodge"]]
     [:button {:type "button"
               :class "p-2 rounded-xl text-gray-400 hover:text-gray-600 hover:bg-gray-100"
               :_ "on click toggle .hidden on #mobile-nav-menu toggle .hidden on #mobile-overlay"}
      [:span {:class "sr-only"} "Close menu"]
      (icons/x-icon)]]
    [:div {:class "flex flex-col px-6 py-6 gap-1 flex-1"}
     (for [[label href] nav-links]
       (let [active? (= active-uri href)]
         [:a {:href href
              :class (str "px-4 py-3 text-sm font-medium rounded-xl transition-colors duration-200 "
                          (if active?
                            "bg-brand-50 text-brand-700"
                            "text-gray-700 hover:bg-gray-50 hover:text-brand-700"))}
          label]))]
    [:div {:class "px-6 py-6 border-t border-gray-100"}
     [:div {:class "flex items-center gap-2.5 text-sm text-gray-500"}
      (icons/phone-icon)
      [:div
       [:p "(033) 386 9139"]
       [:p "(033) 346 0177"]]]]]])

(defn navbar [{:keys [uri]}]
  [:header {:class "sticky top-0 z-50 bg-white/80 backdrop-blur-lg border-b border-gray-200/50"}
   [:nav {:class "flex items-center justify-between max-w-6xl mx-auto px-8 h-20"}
    (logo)
    (desktop-links uri)
    [:div {:class "flex items-center gap-3"}
     [:a {:href "/reservations"
          :class (str "group inline-flex items-center gap-1.5 rounded-full bg-brand-600 "
                      "px-5 py-2.5 text-sm font-semibold text-white shadow-sm "
                      "transition-all duration-200 hover:bg-brand-500 hover:shadow-md "
                      "active:bg-brand-700 "
                      "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-brand-500 "
                      "focus-visible:ring-offset-2")}
      "Book Now"
      (icons/arrow-up-right-icon)]
     (mobile-menu-button)]]
   (mobile-menu uri)])
