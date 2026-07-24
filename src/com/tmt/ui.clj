(ns com.tmt.ui
  (:require [cheshire.core :as cheshire]
            [clojure.java.io :as io]
            [com.tmt.settings :as settings]
            [com.biffweb :as biff]
            [ring.middleware.anti-forgery :as csrf]
            [ring.util.response :as ring-response]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.icons :as icons]
            [rum.core :as rum]))

(defn static-path [path]
  (if-some [last-modified (some-> (io/resource (str "public" path))
                                  ring-response/resource-data
                                  :last-modified
                                  (.getTime))]
    (str path "?t=" last-modified)
    path))

(defn base [{:keys [::recaptcha] :as ctx} & body]
  (apply
   biff/base-html
   (-> ctx
       (merge #:base{:title settings/app-name
                     :lang "en-US"
                     :icon "/img/glider.png"
                     :description (str settings/app-name " Description")
                     :image "https://clojure.org/images/clojure-logo-120b.png"})
       (update :base/head (fn [head]
                            (concat [[:link {:rel "preconnect" :href "https://fonts.googleapis.com"}]
                                     [:link {:rel "preconnect" :href "https://fonts.gstatic.com" :crossorigin ""}]
                                     [:link {:rel "stylesheet"
                                             :href "https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap"}]
                                     [:link {:rel "stylesheet" :href (static-path "/css/main.css")}]
                                     [:script {:src (static-path "/js/main.js")}]
                                     [:script {:src "https://unpkg.com/htmx.org@2.0.7"}]
                                     [:script {:src "https://unpkg.com/htmx-ext-ws@2.0.2/ws.js"}]
                                     [:script {:src "https://unpkg.com/hyperscript.org@0.9.14"}]
                                     (when recaptcha
                                       [:script {:src "https://www.google.com/recaptcha/api.js"
                                                 :async "async" :defer "defer"}])]
                                    head))))
   (concat body
           [[:a {:href "https://wa.me/27333869139" :target "_blank" :rel "noopener noreferrer"
                 :class (str "fixed bottom-6 right-6 z-50 flex size-14 items-center justify-center "
                             "rounded-full bg-green-500 text-white shadow-lg "
                             "transition-all duration-300 hover:bg-green-600 hover:scale-110 "
                             "hover:shadow-xl focus-visible:outline-none focus-visible:ring-2 "
                             "focus-visible:ring-green-500 focus-visible:ring-offset-2")}
             (icons/whatsapp-icon)]])))

(defn page [ctx & body]
  (base
   ctx
   [:.min-h-screen.flex.flex-col
     (nav/navbar ctx)
     [:main.flex-1
     body]]))

(defn on-error [{:keys [status ex] :as ctx}]
  {:status status
   :headers {"content-type" "text/html"}
   :body (rum/render-static-markup
          (page
           ctx
           [:h1.text-lg.font-bold
            (if (= status 404)
              "Page not found."
              "Something went wrong.")]))})
