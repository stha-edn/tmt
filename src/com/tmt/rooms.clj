(ns com.tmt.rooms
  (:require [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.components.shared :as shared]
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

(defn- amenity-pill [text]
  [:li {:class "flex items-center gap-2 text-sm text-gray-600"}
   (icons/check-icon)
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
     [:span {:class "flex items-center gap-1.5"} (icons/bed-icon) bed]
     (when capacity
       [:span {:class "flex items-center gap-1.5"} (icons/users-icon) capacity])]
    [:ul {:class "mt-4 grid grid-cols-1 gap-2 pl-0 list-none my-0 sm:grid-cols-2"}
     (map amenity-pill amenities)]
    [:div {:class "mt-6 pt-4 border-t border-gray-100"}
     [:a {:href (str "mailto:info@tmtours.co.za?subject=" (java.net.URLEncoder/encode (str "Room enquiry: " name) "UTF-8"))
          :class (str "group/link inline-flex items-center gap-1.5 text-sm font-semibold "
                      "text-brand-600 hover:text-brand-800")}
      "Enquire about this room"
      (icons/arrow-icon)]]]])

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
  (shared/cta-section
   {:title "Ready to Book Your Stay?"
    :description "Send us your preferred room type, check-in date and length of stay, and we'll take it from there."
    :children [(shared/primary-button "Make a Reservation" {:href "/reservations"})
               (shared/secondary-button "info@tmtours.co.za" {:href "mailto:info@tmtours.co.za"})]}))

(defn rooms-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
   [:main
    (shared/hero-section
     {:badge "Globe Road & New England Road"
      :title "Rooms & Rates"
      :description (str "Two guest lodges in the quiet, tranquil suburb of Scottsville, Pietermaritzburg — "
                        "ideally suited for individuals, executives and families. Bed only, B&B, DB&B and "
                        "self catering options available at both locations.")})
    (rooms-grid-section)
    (locations-section)
    (cta-section)]
   (footer/footer)))

(def module
  {:routes ["/rooms" {:get rooms-page}]})
