(ns com.tmt.ui.components.shared
  (:require [com.tmt.ui.icons :as icons]))

(defn primary-button [label attrs]
  [:a (merge
       {:class (str "group inline-flex items-center gap-1.5 rounded-full bg-white text-brand-800 "
                     "px-6 py-3 text-sm font-semibold shadow-sm transition-colors hover:bg-brand-50 "
                     "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-white "
                     "focus-visible:ring-offset-2 focus-visible:ring-offset-brand-800")}
       attrs)
   label
   (icons/arrow-icon)])

(defn secondary-button [label attrs]
  [:a (merge
       {:class (str "rounded-full ring-1 ring-inset ring-white/60 px-6 py-3 text-sm font-semibold "
                     "transition-colors hover:bg-white/10 focus-visible:outline-none "
                     "focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-offset-2 "
                     "focus-visible:ring-offset-brand-800")}
       attrs)
   label])

(defn hero-section [{:keys [id badge title description children section-class before py]
                     :or {py "py-20"}}]
  [:section (merge
             (when id {:id id})
             {:class (str "relative isolate overflow-hidden bg-gradient-to-br from-brand-900 to-brand-700 text-white "
                          section-class)})
   before
   [:div {:aria-hidden "true" :class "absolute inset-0 -z-10"}
    [:div {:class "absolute -top-24 -left-24 size-96 rounded-full bg-brand-500 opacity-30 blur-3xl"}]
    [:div {:class "absolute -bottom-24 -right-24 size-96 rounded-full bg-brand-400 opacity-20 blur-3xl"}]]
   [:div {:class (str "max-w-6xl mx-auto px-8 text-center " py)}
    [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-200"} badge]
    [:h1 {:class "mt-3 text-balance text-4xl sm:text-5xl font-bold"} title]
    [:p {:class "mt-4 text-pretty text-lg text-brand-100 max-w-2xl mx-auto"} description]
    children]])

(defn cta-section [{:keys [title description children]}]
  [:section {:class "relative isolate overflow-hidden bg-gradient-to-br from-brand-900 to-brand-700 text-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 text-center"}
    [:h2 {:class "text-balance text-3xl font-bold"} title]
    [:p {:class "mt-3 text-pretty text-brand-100 max-w-xl mx-auto"} description]
    (into [:div {:class "mt-8 flex flex-col sm:flex-row items-center justify-center gap-4"}]
          children)]])
