(ns com.tmt.reservations
  (:require [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.components.shared :as shared]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]))

(defn- hero-section []
  (shared/hero-section
   {:badge "Book Your Stay"
    :title "Make a Reservation"
    :description "Call us directly or send an email — we'll get you sorted right away."}))

(defn- contact-card [{:keys [location address phone alt-phone fax map-query email]}]
  [:div {:class (str "rounded-2xl bg-white p-6 ring-1 ring-gray-900/5 "
                      "shadow-md transition-all duration-300 "
                      "hover:-translate-y-1.5 hover:shadow-xl")}
   [:div {:class "flex items-start gap-3"}
    (icons/pin-icon)
    [:div
     [:h3 {:class "text-lg font-bold text-gray-900"} location]
     [:p {:class "mt-1 text-sm text-gray-600"} address]]]
   [:dl {:class "mt-4 space-y-1 text-sm text-gray-700"}
    [:div {:class "flex items-center gap-2"}
     (icons/phone-icon)
     [:span [:a {:href (str "tel:" (clojure.string/replace phone " " ""))
                 :class "text-brand-600 hover:text-brand-800"} phone]]]
    (when alt-phone
      [:div {:class "flex items-center gap-2"}
       (icons/phone-icon)
       [:span [:a {:href (str "tel:" (clojure.string/replace alt-phone " " ""))
                   :class "text-brand-600 hover:text-brand-800"} alt-phone]]])
    (when fax
      [:div {:class "flex items-center gap-2"}
       [:span {:class "text-xs font-semibold uppercase tracking-wide text-gray-400"} "Fax"]
       [:span {:class "text-gray-600"} fax]])]
   [:div {:class "mt-4 aspect-video overflow-hidden rounded-xl ring-1 ring-black/5"}
    [:iframe {:src (str "https://maps.google.com/maps?q=" map-query "&output=embed")
              :width "100%" :height "100%"
              :style {:border 0}
              :allowfullscreen true
              :loading "lazy"}]]
   [:div {:class "mt-4 pt-4 border-t border-gray-100"}
    [:a {:href (str "mailto:" email)
         :class (str "group inline-flex items-center gap-1.5 text-sm font-semibold "
                     "text-brand-600 hover:text-brand-800")}
     (icons/mail-icon)
     email
     (icons/arrow-icon)]]])

(defn- call-to-book-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 md:py-24"}
    (shared/section-heading "Call to Book" "Talk to Us Directly")
    [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed max-w-2xl"}
     "Prefer to speak to someone? Give us a call or send an email and we'll help you find the perfect room."]
    [:div {:class "mt-10 grid gap-6 md:grid-cols-2"}
     (contact-card
      {:location "Globe Road"
       :address "18 Globe Road, Scottsville, Pietermaritzburg"
       :phone "(033) 386 9139"
       :fax "(086) 656 5306"
       :map-query "18+Globe+Road+Scottsville+Pietermaritzburg"
       :email "info@tmtours.co.za"})
     (contact-card
      {:location "New England Road"
       :address "69 New England Road, Scottsville, Pietermaritzburg"
       :phone "(033) 346 0177"
       :alt-phone "(033) 346 0957"
       :fax "(033) 346 2428"
       :map-query "69+New+England+Road+Scottsville+Pietermaritzburg"
       :email "info@tmtours.co.za"})]]])

(defn- email-section []
  [:section {:class "bg-gray-50"}
   [:div {:class (str "max-w-6xl mx-auto px-8 py-16 md:py-24 "
                       "grid gap-10 md:grid-cols-2 items-center")}
    [:div
     (shared/section-heading "Email Us" "Prefer to Write?")
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      "Include your preferred room type, check-in date, length of stay and any special requests — we'll get back to you as soon as possible."]
     [:a {:href "mailto:info@tmtours.co.za?subject=Room enquiry"
          :class (str "group mt-6 inline-flex items-center gap-2 rounded-full bg-brand-600 "
                      "px-6 py-3 text-sm font-semibold text-white shadow-sm "
                      "transition-colors duration-200 hover:bg-brand-500 active:bg-brand-700")}
      (icons/mail-icon)
      "Send an Email"
      (icons/arrow-icon)]]
    [:div {:class "rounded-2xl bg-white p-6 ring-1 ring-gray-900/5 shadow-md"}
     [:h3 {:class "text-sm font-semibold uppercase tracking-wide text-gray-500"} "What to Include"]
     [:ul {:class "mt-4 space-y-2"}
      (for [item ["Room type preferred" "Check-in date" "Length of stay" "Number of guests" "Special requests"]]
        [:li {:class "flex items-center gap-2 text-sm text-gray-700"}
         (icons/check-icon)
         item])]]]])

(defn- cta-section []
  (shared/cta-section
   {:title "Not Sure Yet?"
    :description "Browse our rooms, take a tour, or explore Pietermaritzburg from the comfort of our guest lodge."
    :children [(shared/primary-button "View the Rooms" {:href "/rooms"})
               (shared/secondary-button "Contact Us" {:href "/contact"})]}))

(defn reservations-page [ctx]
  (ui/base
   ctx
   (nav/navbar ctx)
   [:main
    (hero-section)
    (call-to-book-section)
    (email-section)
    (cta-section)]
   (footer/footer)))

(def module
  {:routes ["/reservations" {:get reservations-page}]})
