(ns com.tmt.gallery
  (:require [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.components.shared :as shared]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]))

(def ^:private categories
  [{:id "globe"
    :title "Globe Road"
    :images ["/img/gallery/globe_gallery_1.JPG"
             "/img/gallery/globe_gallery_2.JPG"
             "/img/gallery/globe_gallery_3.JPG"
             "/img/gallery/globe_gallery_6.JPG"]}
   {:id "newengland"
    :title "New England Road"
    :images ["/img/gallery/newengland_gallery_1.JPG"
             "/img/gallery/newengland_gallery_3.JPG"
             "/img/gallery/newengland_gallery_5.JPG"
             "/img/gallery/newengland_gallery_6.JPG"]}
   {:id "tours"
    :title "Tours"
    :images ["/img/gallery/tours_gallery_1.JPG"
             "/img/gallery/tours_gallery_3.JPG"
             "/img/gallery/tours_gallery_4.JPG"
             "/img/gallery/tours_gallery_5.JPG"]}])

(defn- gallery-image [src alt]
  [:a {:href src :target "_blank" :rel "noopener"
       :class "group relative block aspect-square overflow-hidden rounded-2xl ring-1 ring-gray-900/5"}
   [:img {:src src :alt alt
          :class "size-full object-cover transition-transform duration-300 group-hover:scale-105"}]
   [:div {:class (str "absolute inset-0 flex items-center justify-center bg-black/0 "
                       "transition-colors duration-200 group-hover:bg-black/40")}
    [:div {:class "opacity-0 transition-opacity duration-200 group-hover:opacity-100"}
     (icons/expand-icon)]]])

(defn- category-section [{:keys [id title images]} idx]
  [:section {:id id :class (if (even? idx) "bg-white" "bg-gray-50")}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 scroll-mt-20"}
    [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Gallery"]
    [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} title]
    [:div {:class "mt-8 grid grid-cols-2 gap-4 sm:grid-cols-4"}
     (map-indexed (fn [i src] (gallery-image src (str title " photo " (inc i)))) images)]]])

(defn gallery-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
   [:main
    (shared/hero-section
     {:badge "Take a Look"
      :title "Gallery"
      :description "A glimpse of Globe Road, New England Road, and our tours in action."
      :children [:div {:class "mt-8 flex flex-wrap items-center justify-center gap-3"}
                 (for [{:keys [id title]} categories]
                   [:a {:href (str "#" id)
                        :class (str "rounded-full ring-1 ring-inset ring-white/60 px-5 py-2 text-sm font-semibold "
                                    "transition-colors hover:bg-white/10")}
                    title])]})
    (map-indexed (fn [idx cat] (category-section cat idx)) categories)]
   (footer/footer)))

(def module
  {:routes ["/gallery" {:get gallery-page}]})
