(ns com.tmt.about
  (:require [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.components.shared :as shared]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]))

(def ^:private locations
  [{:name "Globe Road"
    :image "/img/about/aboutglobe.jpg"
    :alt "TM Guest Lodge, Globe Road"
    :description (str "Situated in a quiet and tranquil suburb of Pietermaritzburg, "
                       "Globe Road offers luxurious, modern accommodation for travellers "
                       "and busy executives alike.")}
   {:name "New England Road"
    :image "/img/about/aboutnewengland.jpg"
    :alt "TM Guest Lodge, New England Road"
    :description (str "Formerly known as Little Bigg Guest House, this property is "
                       "nestled in picturesque Pietermaritzburg and features fireplaces, "
                       "summer decks and a swimming pool, with the Golden Horse Casino "
                       "nearby.")}])

(def ^:private values
  [{:title "Royal Hospitality"
    :description "Facilities to make our guests feel like royalty, with a guaranteed memorable stay."}
   {:title "Calm Meets Convenience"
    :description "A calming atmosphere in a quiet suburb, without giving up the convenience of city life."}
   {:title "Two Locations, One Standard"
    :description "Globe Road and New England Road offer the same rooms, rates and standard of comfort."}])

(defn- location-card [{:keys [name image alt description]} idx]
  (let [reversed (odd? idx)]
    [:div {:class "grid gap-10 md:grid-cols-2 items-center"}
     [:img {:src image :alt alt
            :class (str "aspect-video w-full rounded-3xl object-cover ring-1 ring-gray-900/5 "
                        (if reversed "md:order-2" "md:order-1"))}]
     [:div {:class (if reversed "md:order-1" "md:order-2")}
      [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Our Locations"]
      [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} name]
      [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"} description]]]))

(defn- locations-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 flex flex-col gap-16"}
    (map-indexed (fn [idx loc] (location-card loc idx)) locations)]])
;; flex flex-col gap-16 replaces the previous space-y-16 on a non-flex container

(defn- value-card [{:keys [title description]}]
  [:div {:class (str "rounded-2xl bg-white p-6 ring-1 ring-gray-900/5 shadow-sm "
                      "transition-all duration-200 hover:-translate-y-1 hover:shadow-lg")}
   [:h3 {:class "text-base font-bold text-gray-900"} title]
   [:p {:class "mt-2 text-sm text-gray-600 leading-relaxed"} description]])

(defn- values-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16"}
    [:div {:class "max-w-2xl"}
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Why Guests Choose Us"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "What We Stand For"]]
    [:div {:class "mt-10 grid gap-6 sm:grid-cols-3"}
     (map value-card values)]]])

(defn- tours-tie-in-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-2 items-center"}
    [:div
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "One Family, Two Services"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "TM Tours"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      (str "Alongside the lodge, TM Tours grew out of a small service ferrying school "
           "children from Imbali and Edendale to schools in the city. Today that same "
           "reliability serves small, medium and large business enterprises, public "
           "institutions, and guests looking to explore KwaZulu-Natal and beyond.")]
     [:a {:href "/tours"
          :class (str "group mt-4 inline-flex items-center gap-1.5 text-sm font-semibold "
                      "text-brand-600 hover:text-brand-800")}
      "Explore Our Tours"
      (icons/arrow-icon)]]
    [:img {:src "/img/gallery/tours_gallery_1.JPG"
           :alt "TM Tours fleet"
           :class "aspect-video w-full rounded-3xl object-cover ring-1 ring-gray-900/5"}]]])

(defn- cta-section []
  (shared/cta-section
   {:title "Come and See for Yourself"
    :description "Browse our rooms, take a look through the gallery, or get in touch to plan your stay."
    :children [(shared/primary-button "View the Rooms" {:href "/rooms"})
               (shared/secondary-button "Get in Touch" {:href "/contact"})]}))

(defn about-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
   [:main
    (shared/hero-section
     {:badge "Pietermaritzburg, KwaZulu-Natal"
      :title "About TM Guest Lodge"
      :description (str "One lodge, two locations, and a tour company that grew out of the same "
                        "commitment to looking after people.")})
    (locations-section)
    (values-section)
    (tours-tie-in-section)
    (cta-section)]
   (footer/footer)))

(def module
  {:routes ["/about" {:get about-page}]})
