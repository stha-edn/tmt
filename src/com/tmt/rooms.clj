(ns com.tmt.rooms
  (:require [com.tmt.ui :as ui]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]))

(def ^:private rooms
  [{:name "Single / Double Room"
    :image "/img/rooms/temp_room_1.jpg"
    :alt "Double bed in a Single/Double room at TM Guest Lodge"
    :bed "King bed"
    :rate "380"
    :amenities ["Flat-screen TV" "En-suite bathroom" "Bar fridge" "Microwave" "Tea/coffee facility"]}
   {:name "Twin Room"
    :image "/img/rooms/temp_room_2.jpg"
    :alt "Twin beds at TM Guest Lodge"
    :bed "Two single beds"
    :rate "440"
    :amenities ["Flat-screen TV" "En-suite bathroom" "Bar fridge" "Microwave" "Tea/coffee facility"]}
   {:name "Self Catering"
    :image "/img/rooms/temp_room_4.jpg"
    :alt "Self catering kitchenette at TM Guest Lodge"
    :bed "King or single beds"
    :rate "450"
    :amenities ["Flat-screen TV" "Stove & oven" "Cutlery & crockery" "Microwave" "Bar fridge"]}
   {:name "Cottage / Family Room"
    :image "/img/rooms/temp_room_3.jpg"
    :alt "Family cottage exterior at TM Guest Lodge"
    :bed "King bed and single beds"
    :capacity "Sleeps up to 6"
    :rate "480"
    :amenities ["Flat-screen TV" "Stove & oven" "Cutlery & crockery" "Microwave" "Bar fridge"]}])

(defn- arrow-icon []
  [:svg {:viewBox "0 0 20 20"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 transition-transform duration-200 group-hover:translate-x-0.5"
         :style {:display "inline" :fill "none" :stroke "currentcolor" :stroke-width "2"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M4.5 10h11m0 0-4-4m4 4-4 4"}]])

(defn- check-icon []
  [:svg {:viewBox "0 0 20 20"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0 text-brand-600"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "2"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M4 10.5 8 14.5 16 5.5"}]])

(defn- bed-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0 text-gray-400"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M3 18v-6a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v6M3 18v2M21 18v2M3 12V7a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v3M13 9h6a2 2 0 0 1 2 2v1"}]])

(defn- users-icon []
  [:svg {:viewBox "0 0 24 24"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 shrink-0 text-gray-400"
         :style {:display "block" :fill "none" :stroke "currentcolor" :stroke-width "1.75"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round"
           :d "M17 20v-1a4 4 0 0 0-4-4H7a4 4 0 0 0-4 4v1M15 11a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM21 20v-1a4 4 0 0 0-3-3.87M15.5 5.13A3 3 0 0 1 18 8a3 3 0 0 1-1.28 2.46"}]])

(defn- hero-section []
  [:section {:class "relative isolate overflow-hidden bg-gradient-to-br from-brand-900 to-brand-700 text-white"}
   [:div {:aria-hidden "true" :class "absolute inset-0 -z-10"}
    [:div {:class "absolute -top-24 -right-24 size-96 rounded-full bg-brand-500 opacity-30 blur-3xl"}]
    [:div {:class "absolute -bottom-24 -left-24 size-96 rounded-full bg-brand-400 opacity-20 blur-3xl"}]]
   [:div {:class "max-w-6xl mx-auto px-8 py-20 text-center"}
    [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-200"} "Globe Road & New England Road"]
    [:h1 {:class "mt-3 text-balance text-4xl sm:text-5xl font-bold"} "Rooms & Rates"]
    [:p {:class "mt-4 text-pretty text-lg text-brand-100 max-w-2xl mx-auto"}
     (str "Two guest lodges in the quiet, tranquil suburb of Scottsville, Pietermaritzburg — "
          "ideally suited for individuals, executives and families. Bed only, B&B, DB&B and "
          "self catering options available at both locations.")]]])

(defn- amenity-pill [text]
  [:li {:class "flex items-center gap-2 text-sm text-gray-600"}
   (check-icon)
   text])

(defn- room-card [{:keys [name image alt bed capacity rate amenities]}]
  [:div {:class (str "group flex flex-col overflow-hidden rounded-3xl bg-white ring-1 ring-gray-900/5 "
                      "shadow-sm transition-all duration-200 hover:-translate-y-1 hover:shadow-lg")}
   [:div {:class "relative aspect-[4/3] overflow-hidden"}
    [:img {:src image :alt alt
           :class "size-full object-cover transition-transform duration-300 group-hover:scale-105"}]
    [:div {:class "absolute top-3 right-3 rounded-full bg-white/95 px-3 py-1 text-xs font-semibold text-brand-800 shadow-sm"}
     "From R " rate " / night"]]
   [:div {:class "flex flex-1 flex-col p-6"}
    [:h3 {:class "text-lg font-bold text-gray-900"} name]
    [:div {:class "mt-2 flex flex-wrap gap-x-4 gap-y-1 text-sm text-gray-500"}
     [:span {:class "flex items-center gap-1.5"} (bed-icon) bed]
     (when capacity
       [:span {:class "flex items-center gap-1.5"} (users-icon) capacity])]
    [:ul {:class "mt-4 grid grid-cols-1 gap-2 pl-0 list-none my-0 sm:grid-cols-2"}
     (map amenity-pill amenities)]
    [:div {:class "mt-6 pt-4 border-t border-gray-100"}
     [:a {:href (str "mailto:info@tmtours.co.za?subject=" (java.net.URLEncoder/encode (str "Room enquiry: " name) "UTF-8"))
          :class (str "group/link inline-flex items-center gap-1.5 text-sm font-semibold "
                      "text-brand-600 hover:text-brand-800")}
      "Enquire about this room"
      (arrow-icon)]]]])

(defn- rooms-grid-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16"}
    [:div {:class "max-w-2xl"}
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Accommodation"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Choose Your Room"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      "Every room comes with facilities to make our guests feel like royalty. Rates apply year-round and are subject to availability and change."]]
    [:div {:class "mt-10 grid gap-8 sm:grid-cols-2"}
     (map room-card rooms)]]])

(defn- locations-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-2 items-center"}
    [:img {:src "/img/gallery/globe_gallery_3.JPG"
           :alt "Balcony at TM Guest Lodge"
           :class "aspect-video w-full rounded-3xl object-cover ring-1 ring-gray-900/5"}]
    [:div
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Two Locations"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Globe Road & New England Road"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      (str "Both properties are situated in strategic, quiet locations in Scottsville, "
           "Pietermaritzburg, and offer the same room types and rate structure — so wherever "
           "you stay, you get the same standard of comfort.")]
     [:dl {:class "mt-6 grid grid-cols-1 gap-4 sm:grid-cols-2"}
      [:div {:class "rounded-2xl bg-gray-50 p-4 ring-1 ring-gray-900/5"}
       [:dt {:class "text-sm font-semibold text-gray-900"} "Globe Road"]
       [:dd {:class "mt-1 text-sm text-gray-600"} "(033) 386 9139"]]
      [:div {:class "rounded-2xl bg-gray-50 p-4 ring-1 ring-gray-900/5"}
       [:dt {:class "text-sm font-semibold text-gray-900"} "New England Road"]
       [:dd {:class "mt-1 text-sm text-gray-600"} "(033) 346 0177"]]]]]])

(defn- cta-section []
  [:section {:class "relative isolate overflow-hidden bg-gradient-to-br from-brand-900 to-brand-700 text-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 text-center"}
    [:h2 {:class "text-balance text-3xl font-bold"} "Ready to Book Your Stay?"]
    [:p {:class "mt-3 text-pretty text-brand-100 max-w-xl mx-auto"}
     "Send us your preferred room type, check-in date and length of stay, and we'll take it from there."]
    [:div {:class "mt-8 flex flex-col sm:flex-row items-center justify-center gap-4"}
     [:a {:href "/reservations"
          :class (str "group inline-flex items-center gap-1.5 rounded-full bg-white text-brand-800 "
                      "px-6 py-3 text-sm font-semibold shadow-sm transition-colors hover:bg-brand-50 "
                      "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-white "
                      "focus-visible:ring-offset-2 focus-visible:ring-offset-brand-800")}
      "Make a Reservation"
      (arrow-icon)]
     [:a {:href "mailto:info@tmtours.co.za"
          :class (str "rounded-full ring-1 ring-inset ring-white/60 px-6 py-3 text-sm font-semibold "
                      "transition-colors hover:bg-white/10 focus-visible:outline-none "
                      "focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-offset-2 "
                      "focus-visible:ring-offset-brand-800")}
      "info@tmtours.co.za"]]]])

(defn rooms-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
   [:main
    (hero-section)
    (rooms-grid-section)
    (locations-section)
    (cta-section)]
   (footer/footer)))

(def module
  {:routes ["/rooms" {:get rooms-page}]})
