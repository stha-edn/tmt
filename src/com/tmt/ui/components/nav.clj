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
  [:div.inline-flex
   [:a {:class "flex items-center gap-2" :href "/"}
    [:span {:class "text-xl font-bold tracking-tight text-brand-700"} "TM"]
    [:span {:class "hidden sm:block text-xl font-semibold text-gray-800"} "Guest Lodge"]]])

(defn- desktop-links []
  [:div {:class "hidden md:flex items-center gap-6"}
   (for [[label href] nav-links]
     [:a {:href href
          :class "text-sm font-medium text-gray-700 hover:text-brand-700"}
      label])])

(defn- phone-numbers []
  [:div {:class "hidden md:flex items-center gap-2 text-sm text-gray-700"}
   (icons/phone-icon)
   [:span "(033) 386 9139 / (033) 346 0177"]])

(defn- mobile-menu-button []
  [:button {:type "button"
            :class "md:hidden inline-flex items-center justify-center p-2 border rounded-full hover:bg-gray-100"
            :_ "on click toggle .hidden on #mobile-nav-menu"}
   (icons/menu-icon)])

(defn- mobile-menu []
  [:div#mobile-nav-menu {:class "hidden md:hidden absolute top-20 left-0 w-full bg-white border-t shadow-lg z-10"}
   [:div {:class "flex flex-col px-8 py-4 gap-3"}
    (for [[label href] nav-links]
      [:a {:href href
           :class "text-sm font-medium text-gray-700 hover:text-brand-700"}
       label])
    [:div {:class "flex items-center gap-2 text-sm text-gray-700 pt-2 border-t"}
     (icons/phone-icon)
     [:span "(033) 386 9139 / (033) 346 0177"]]]])

(defn navbar []
  [:.relative
   [:nav {:class "bg-white w-full flex relative justify-between items-center mx-auto max-w-6xl px-8 h-20"}
    (logo)
    (desktop-links)
    [:div {:class "flex items-center gap-4"}
     (phone-numbers)
     [:a {:href "/reservations"
          :class (str "group hidden sm:inline-flex items-center gap-1.5 rounded-full bg-brand-600 "
                      "px-5 py-2.5 text-sm font-semibold text-white shadow-sm "
                      "transition-colors duration-200 hover:bg-brand-500 active:bg-brand-700 "
                      "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-brand-500 "
                      "focus-visible:ring-offset-2")}
      "Book Now"
      (icons/arrow-icon)]
     (mobile-menu-button)]]
   (mobile-menu)])
