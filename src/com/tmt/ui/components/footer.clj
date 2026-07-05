(ns com.tmt.ui.components.footer)

(def ^:private explore-links
  [["Home" "/"]
   ["Rooms" "/rooms"]
   ["Tours" "/tours"]
   ["Gallery" "/gallery"]
   ["About" "/about"]
   ["Contact" "/contact"]])

(defn- social-icon [path]
  [:a
   {:href "#"
    :class (str "flex items-center justify-center size-9 rounded-full bg-gray-800 "
                "transition-all duration-200 hover:bg-blue-600 hover:scale-110")}
   [:svg
    {:viewBox "0 0 24 24"
     :xmlns "http://www.w3.org/2000/svg"
     :aria-hidden "true"
     :focusable "false"
     :class "size-4"
     :style {:display "block" :fill "currentcolor"}}
    [:path {:d path}]]])

(defn- about-column []
  [:div
   [:h3 {:class "text-lg font-semibold text-white"} "TM Guest Lodge"]
   [:p {:class "mt-3 text-sm leading-relaxed"}
    "Globe and New England Road, Pietermaritzburg. Two locations, one standard of comfort."]])

(defn- contact-column []
  [:div
   [:h4 {:class "text-sm font-semibold text-white uppercase tracking-wide"} "Contact"]
   [:ul {:class "mt-3 space-y-2 text-sm"}
    [:li "Globe Road: (033) 386 9139"]
    [:li "New England Road: (033) 346 0177"]
    [:li [:a {:href "mailto:info@tmtours.co.za" :class "hover:text-white"} "info@tmtours.co.za"]]]])

(defn- explore-column []
  [:div
   [:h4 {:class "text-sm font-semibold text-white uppercase tracking-wide"} "Explore"]
   [:ul {:class "mt-3 grid grid-cols-2 gap-2 text-sm"}
    (for [[label href] explore-links]
      [:li [:a {:href href :class "hover:text-white"} label]])]])

(defn- bottom-bar []
  [:div {:class "border-t border-gray-800"}
   [:div {:class "max-w-6xl mx-auto px-8 py-6 flex flex-col sm:flex-row items-center justify-between gap-4"}
    [:p {:class "text-xs text-gray-500"} "© TM Guest Lodge. All rights reserved."]
    [:div {:class "flex items-center gap-3"}
     (social-icon "M22 12a10 10 0 10-11.56 9.88v-6.99H7.9V12h2.54V9.8c0-2.5 1.5-3.89 3.78-3.89 1.1 0 2.24.2 2.24.2v2.46h-1.26c-1.24 0-1.63.77-1.63 1.56V12h2.78l-.44 2.89h-2.34v6.99A10 10 0 0022 12z")
     (social-icon "M22 5.9c-.63.28-1.3.47-2 .56.72-.43 1.28-1.12 1.54-1.94-.67.4-1.42.68-2.22.84A3.48 3.48 0 0016.7 4c-1.94 0-3.5 1.57-3.5 3.5 0 .27.03.54.09.79A9.9 9.9 0 013 4.9a3.5 3.5 0 001.08 4.67c-.57-.02-1.1-.17-1.57-.43v.04c0 1.7 1.2 3.13 2.8 3.45-.3.08-.6.12-.92.12-.22 0-.44-.02-.65-.06a3.5 3.5 0 003.27 2.44A7.02 7.02 0 012 16.56 9.9 9.9 0 007.29 18c6.34 0 9.8-5.25 9.8-9.8 0-.15 0-.3-.01-.44A7 7 0 0022 5.9z")]]])

(defn footer []
  [:footer {:class "bg-gray-900 text-gray-300"}
   [:div {:class "max-w-6xl mx-auto px-8 py-12 grid gap-10 md:grid-cols-3"}
    (about-column)
    (contact-column)
    (explore-column)]
   (bottom-bar)])
