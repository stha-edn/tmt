(ns com.tmt.about
  (:require [com.tmt.ui :as ui]
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

(defn- arrow-icon []
  [:svg {:viewBox "0 0 20 20"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 transition-transform duration-200 group-hover:translate-x-0.5"
         :style {:display "inline" :fill "none" :stroke "currentcolor" :stroke-width "2"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M4.5 10h11m0 0-4-4m4 4-4 4"}]])

(defn- hero-section []
  [:section {:class "relative isolate overflow-hidden bg-gradient-to-br from-brand-900 to-brand-700 text-white"}
   [:div {:aria-hidden "true" :class "absolute inset-0 -z-10"}
    [:div {:class "absolute -top-24 -left-24 size-96 rounded-full bg-brand-500 opacity-30 blur-3xl"}]
    [:div {:class "absolute -bottom-24 -right-24 size-96 rounded-full bg-brand-400 opacity-20 blur-3xl"}]]
   [:div {:class "max-w-6xl mx-auto px-8 py-20 text-center"}
    [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-200"} "Pietermaritzburg, KwaZulu-Natal"]
    [:h1 {:class "mt-3 text-balance text-4xl sm:text-5xl font-bold"} "About TM Guest Lodge"]
    [:p {:class "mt-4 text-pretty text-lg text-brand-100 max-w-2xl mx-auto"}
     (str "One lodge, two locations, and a tour company that grew out of the same "
          "commitment to looking after people.")]]])

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
   [:div {:class "max-w-6xl mx-auto px-8 py-16 space-y-16"}
    (map-indexed (fn [idx loc] (location-card loc idx)) locations)]])

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
      (arrow-icon)]]
    [:img {:src "/img/gallery/tours_gallery_1.JPG"
           :alt "TM Tours fleet"
           :class "aspect-video w-full rounded-3xl object-cover ring-1 ring-gray-900/5"}]]])

(defn- cta-section []
  [:section {:class "relative isolate overflow-hidden bg-gradient-to-br from-brand-900 to-brand-700 text-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 text-center"}
    [:h2 {:class "text-balance text-3xl font-bold"} "Come and See for Yourself"]
    [:p {:class "mt-3 text-pretty text-brand-100 max-w-xl mx-auto"}
     "Browse our rooms, take a look through the gallery, or get in touch to plan your stay."]
    [:div {:class "mt-8 flex flex-col sm:flex-row items-center justify-center gap-4"}
     [:a {:href "/rooms"
          :class (str "group inline-flex items-center gap-1.5 rounded-full bg-white text-brand-800 "
                      "px-6 py-3 text-sm font-semibold shadow-sm transition-colors hover:bg-brand-50 "
                      "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-white "
                      "focus-visible:ring-offset-2 focus-visible:ring-offset-brand-800")}
      "View the Rooms"
      (arrow-icon)]
     [:a {:href "/contact"
          :class (str "rounded-full ring-1 ring-inset ring-white/60 px-6 py-3 text-sm font-semibold "
                      "transition-colors hover:bg-white/10 focus-visible:outline-none "
                      "focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-offset-2 "
                      "focus-visible:ring-offset-brand-800")}
      "Get in Touch"]]]])

(defn about-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
   [:main
    (hero-section)
    (locations-section)
    (values-section)
    (tours-tie-in-section)
    (cta-section)]
   (footer/footer)))

(def module
  {:routes ["/about" {:get about-page}]})
