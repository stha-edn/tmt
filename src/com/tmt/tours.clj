(ns com.tmt.tours
  (:require [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.components.shared :as shared]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]))

(def ^:private day-tours
  [{:name "Hluhluwe Game Reserve"
    :image "/img/gallery/tours_gallery_1.JPG"
    :alt "TM Tours vehicles ready for a day tour"
    :duration "Full day"
    :description (str "A 3-hour drive from Durban into 96 hectares of protected wildlife "
                       "sanctuary, with game viewing and a picnic along the way.")}
   {:name "Sani Pass & Lesotho"
    :image "/img/gallery/tours_gallery_3.JPG"
    :alt "TM Tours fleet used for the Sani Pass excursion"
    :duration "Full day, departs 6:00am"
    :description (str "A 4x4 journey up into the mountains, with visits to Basotho villages "
                       "and an authentic taste of highland culture.")}
   {:name "Battlefields Tour"
    :image "/img/gallery/tours_gallery_4.JPG"
    :alt "TM Tours coaches used for the Battlefields tour"
    :duration "Full day"
    :description (str "Anglo-Zulu War history at Rorke's Drift and Gingindlovu, with a "
                       "scenic drive along the North Coast.")}])

(def ^:private half-day-tours
  ["Pietermaritzburg & Howick city tour"
   "Township experience tour"])

(def ^:private more-ways
  [{:title "National Tours"
    :description "Game reserves, historic and political landmarks, and cultural tours across South Africa."}
   {:title "Big City Tours"
    :description "Guided trips to Johannesburg, Cape Town, Durban and Port Elizabeth."}
   {:title "School Excursions & Charters"
    :description "Vacation programs and transport for schools, businesses and public institutions."}])

(defn- tour-card [{:keys [name image alt duration description]}]
  [:div {:class (str "group flex flex-col overflow-hidden rounded-3xl bg-white ring-1 ring-gray-900/5 "
                      "shadow-sm transition-all duration-200 hover:-translate-y-1 hover:shadow-lg")}
   [:div {:class "relative aspect-[4/3] overflow-hidden"}
    [:img {:src image :alt alt
           :class "size-full object-cover transition-transform duration-300 group-hover:scale-105"}]]
   [:div {:class "flex flex-1 flex-col p-6"}
    [:div {:class "flex items-center gap-1.5 text-xs font-semibold uppercase tracking-wide text-brand-600"}
     (icons/clock-icon)
     duration]
    [:h3 {:class "mt-2 text-lg font-bold text-gray-900"} name]
    [:p {:class "mt-2 text-sm text-gray-600 leading-relaxed flex-1"} description]]])

(defn- day-tours-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16"}
    [:div {:class "max-w-2xl"}
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Day Tours"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Popular Excursions"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      "Whether it's wildlife, mountains or history you're after, our day tours make for an easy, guided outing."]]
    [:div {:class "mt-10 grid gap-8 sm:grid-cols-2 lg:grid-cols-3"}
     (map tour-card day-tours)]]])

(defn- half-day-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-2 items-center"}
    [:div
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Half-Day Tours"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Short on Time? No Problem"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      "For guests who want a taste of the region without giving up a full day, we offer:"]
     [:ul {:class "mt-6 space-y-3 list-none pl-0 my-0"}
      (for [t half-day-tours]
        [:li {:class "flex items-center gap-2 text-gray-700"} (icons/check-icon) t])]]
    [:img {:src "/img/rooms/temp_room_3.jpg"
           :alt "TM Guest Lodge, a comfortable base for half-day tours"
           :class "aspect-video w-full rounded-3xl object-cover ring-1 ring-gray-900/5"}]]])

(defn- more-ways-card [{:keys [title description]}]
  [:div {:class (str "rounded-2xl bg-gray-50 p-6 ring-1 ring-gray-900/5 transition-all duration-200 "
                      "hover:-translate-y-1 hover:shadow-lg hover:bg-white")}
   [:h3 {:class "text-base font-bold text-gray-900"} title]
   [:p {:class "mt-2 text-sm text-gray-600 leading-relaxed"} description]])

(defn- more-ways-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16"}
    [:div {:class "max-w-2xl"}
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "And More"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "More Ways to Travel With Us"]]
    [:div {:class "mt-10 grid gap-6 sm:grid-cols-3"}
     (map more-ways-card more-ways)]]])

(defn- fleet-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-2 items-center"}
    [:img {:src "/img/gallery/tours_gallery_1.JPG"
           :alt "The TM Tours fleet of minibuses and coaches"
           :class "aspect-video w-full rounded-3xl object-cover ring-1 ring-gray-900/5"}]
    [:div
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Our Fleet"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Safe, Reliable, Professional"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      (str "Every trip is run with safe, well-maintained vehicles and licensed, trained "
           "drivers who put customer service first — helping businesses and institutions "
           "lower their transport operating costs.")]
     [:div {:class "mt-6 flex items-start gap-3 rounded-2xl bg-brand-50 p-4 ring-1 ring-brand-600/10"}
      (icons/shield-icon)
      [:p {:class "text-sm text-gray-700"}
       "Licensed, trained drivers and a safety-first standard on every tour, transfer and charter."]]]]])

(defn- history-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-2 items-center"}
    [:img {:src "/img/gallery/tours_gallery_2.JPG"
           :alt "TM Tours vehicle detail"
           :class "aspect-video w-full rounded-3xl object-cover ring-1 ring-gray-900/5"}]
    [:div
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Our Story"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "From School Runs to a Full Touring Service"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      (str "TM Tours started on a small scale, ferrying school children from Imbali and "
           "Edendale to schools in the city. That same commitment to reliability now serves "
           "small, medium and large business enterprises as well as public institutions "
           "across KwaZulu-Natal and beyond.")]
     [:p {:class "mt-3 text-pretty text-gray-600 leading-relaxed"}
      "Our vision is to be recognised as KwaZulu-Natal's leading transportation provider."]]]])

(defn- cta-section []
  (shared/cta-section
   {:title "Let's Plan Your Trip"
    :description "Tell us where you'd like to go, how many people, and when — we'll put together a tour to suit."
    :children [(shared/primary-button "info@tmtours.co.za" {:href "mailto:info@tmtours.co.za"})
                (shared/secondary-button "(033) 386 9139" {:href "tel:+27333869139"})]}))

(defn tours-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
   [:main
    (shared/hero-section
     {:badge "The African Experience in Touring"
      :title "Travel with TM Tours"
      :description (str "Personalised, customisable transport and touring services for individuals, "
                        "schools, and small, medium and large business enterprises.")})
    (history-section)
    (day-tours-section)
    (half-day-section)
    (more-ways-section)
    (fleet-section)
    (cta-section)]
   (footer/footer)))

(def module
  {:routes ["/tours" {:get tours-page}]})
