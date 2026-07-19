(ns com.tmt.ui.components.footer
  (:require [com.tmt.ui.icons :as icons]))

(def ^:private explore-links
  [["Home" "/"]
   ["Rooms" "/rooms"]
   ["Tours" "/tours"]
   ["Gallery" "/gallery"]
   ["About" "/about"]
   ["Contact" "/contact"]])

(defn- social-icon [icon]
  [:a {:href "#"
       :class (str "flex items-center justify-center size-9 rounded-lg "
                   "bg-white/10 text-white/50 "
                   "transition-all duration-200 hover:bg-brand-600 hover:text-white hover:scale-110")}
   (icon)])

(defn- about-column []
  [:div {:class "space-y-4"}
   [:a {:href "/" :class "inline-flex items-center gap-2.5"}
    [:div {:class "flex size-9 items-center justify-center rounded-xl bg-brand-600 text-white shadow-sm"}
     (icons/house-icon)]
    [:div
     [:span {:class "block text-base font-bold text-white leading-tight"} "TM"]
     [:span {:class "block text-xs font-medium text-white/60 leading-tight"} "Guest Lodge"]]]
   [:p {:class "text-sm text-white/70 leading-relaxed max-w-xs"}
    "Globe and New England Road, Scottsville, Pietermaritzburg. Two locations, one standard of comfort."]
   [:div {:class "flex items-center gap-2 pt-1"}
    (social-icon icons/facebook-icon)
    (social-icon icons/twitter-icon)]])

(defn- quick-links []
  [:div
   [:h4 {:class "text-sm font-semibold text-white uppercase tracking-wider"} "Explore"]
   [:ul {:class "mt-4 space-y-3 list-none pl-0 my-0"}
    (for [[label href] explore-links]
      [:li [:a {:href href
                :class "text-sm text-white/70 transition-colors duration-200 hover:text-white"} label]])]])

(defn- contact-info []
  [:div
   [:h4 {:class "text-sm font-semibold text-white uppercase tracking-wider"} "Contact"]
   [:ul {:class "mt-4 space-y-3 list-none pl-0 my-0"}
    [:li
     [:div {:class "flex items-start gap-2.5"}
      [:span {:class "mt-0.5 shrink-0 text-brand-400"}
       (icons/phone-icon)]
      [:div
       [:p {:class "text-sm text-white/60"} "Globe Road:"]
       [:a {:href "tel:+27333869139"
            :class "text-sm font-medium text-white hover:text-brand-300 transition-colors"} "(033) 386 9139"]]]]
    [:li
     [:div {:class "flex items-start gap-2.5"}
      [:span {:class "mt-0.5 shrink-0 text-brand-400"}
       (icons/phone-icon)]
      [:div
       [:p {:class "text-sm text-white/60"} "New England Road:"]
       [:a {:href "tel:+27333460177"
            :class "text-sm font-medium text-white hover:text-brand-300 transition-colors"} "(033) 346 0177"]]]]
    [:li
     [:div {:class "flex items-start gap-2.5"}
      [:span {:class "mt-0.5 shrink-0 text-brand-400"}
       (icons/mail-icon)]
      [:a {:href "mailto:info@tmtours.co.za"
           :class "text-sm font-medium text-white hover:text-brand-300 transition-colors"} "info@tmtours.co.za"]]]]])

(defn- bottom-bar []
  [:div {:class "border-t border-white/10"}
   [:div {:class "max-w-6xl mx-auto px-8 py-6 flex flex-col sm:flex-row items-center justify-between gap-4"}
    [:p {:class "text-xs text-white/40"} "© 2026 TM Guest Lodge. All rights reserved."]
    [:a {:href "#"
         :onclick "window.scrollTo({top:0,behavior:'smooth'});return false"
         :class (str "group inline-flex items-center gap-1.5 text-xs text-white/40 "
                     "hover:text-white transition-colors duration-200")}
     "Back to top"
     [:span {:class "inline-block transition-transform duration-200 group-hover:-translate-y-1"}
       (icons/chevron-up-icon)]]]])

(defn footer []
  [:footer {:class "bg-brand-900"}
   [:div {:class "relative"}
    [:div {:class "absolute top-0 left-0 right-0 h-1 bg-gradient-to-r from-brand-500 via-brand-400 to-brand-500"}]
    [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-3"}
     (about-column)
     (quick-links)
     (contact-info)]
    (bottom-bar)]])
