(ns com.tmt.contact
  (:require [com.biffweb :as biff]
            [com.tmt.email :as email]
            [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.components.shared :as shared]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]
            [rum.core :as rum]))

(def ^:private categories
  ["Guest Lodge" "Travel and Tours" "Reservations" "Newsletter"])

(def ^:private locations
  [{:name "Globe Road"
    :address "18 Globe Road, Scottsville, Pietermaritzburg"
    :phone "(033) 386 9139"
    :fax "(086) 656 5306"
    :directions-jhb ["Exit the N3 at Market Road"
                      "Skip the first traffic lights; turn right at the second light (Washington Road)"
                      "Turn left onto Globe Road"
                      "Look for house number 16-18 on the right"]
    :directions-durban ["Same route as from Johannesburg"]}
   {:name "New England Road"
    :address "69 New England Road, Scottsville, Pietermaritzburg"
    :phone "(033) 346 0177"
    :alt-phone "(033) 346 0957"
    :fax "(033) 346 2428"
    :directions-jhb ["Exit the N3 at New England Road"
                      "Turn right onto New England Road"
                      "At the first traffic light (Ridge Road), turn right into Sanders Road"
                      "The property is first on the left"]
    :directions-durban ["Exit the N3 at New England Road"
                         "Turn left onto New England Road"
                         "At the first traffic light (Ridge Road), turn right into Sanders Road"
                         "The property is first on the left"]}])

(def ^:private email-disabled-notice
  [:.text-sm.mt-4.bg-brand-100.rounded.p-3
   "Until you add API keys for MailerSend, we'll print enquiries to the console instead of emailing them. See config.edn."])

(defn- text-field [{:keys [id label type required] :or {type "text"}}]
  [:div
   [:label {:for id :class "block text-sm font-medium text-gray-700"} label]
   [:input {:id id :name id :type type :required required
            :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                        "text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 "
                        "focus:ring-2 focus:ring-inset focus:ring-brand-600")}]])

(defn- contact-form [{:keys [params]}]
  (biff/form
   {:action "/contact/send" :class "space-y-5"}
   [:div {:class "grid gap-5 sm:grid-cols-2"}
    (text-field {:id "name" :label "Name" :required true})
    (text-field {:id "email" :label "Email" :type "email" :required true})]
   [:div
    [:label {:for "category" :class "block text-sm font-medium text-gray-700"} "Category"]
    [:select {:id "category" :name "category"
              :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                          "text-gray-900 ring-1 ring-inset ring-gray-300 "
                          "focus:ring-2 focus:ring-inset focus:ring-brand-600")}
     (for [c categories]
       [:option {:value c} c])]]
   (text-field {:id "subject" :label "Subject"})
   [:div
    [:label {:for "message" :class "block text-sm font-medium text-gray-700"} "Message"]
    [:textarea {:id "message" :name "message" :rows 5 :required true
                :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                            "text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 "
                            "focus:ring-2 focus:ring-inset focus:ring-brand-600")}]]
   [:button {:type "submit"
             :class (str "group inline-flex items-center gap-1.5 rounded-full bg-brand-600 "
                         "px-6 py-3 text-sm font-semibold text-white shadow-sm "
                         "transition-colors duration-200 hover:bg-brand-500 active:bg-brand-700")}
    "Send Message"
    (icons/arrow-icon)]
   (when-some [error (:error params)]
     [:.text-sm.text-red-600
      (case error
        "missing-fields" "Please fill in your name, email and message."
        "send-failed" "Something went wrong sending your message. Please try again, or call us directly."
        "There was an error.")])
   email-disabled-notice))

(defn- form-section [ctx]
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-12 lg:grid-cols-5"}
    [:div {:class "lg:col-span-3"}
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Send a Message"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Tell Us How We Can Help"]
     (if (= (get-in ctx [:params :sent]) "true")
       [:div {:class "mt-6 rounded-2xl bg-brand-50 p-6 ring-1 ring-brand-600/10"}
        [:p {:class "font-semibold text-brand-800"} "Thanks — your message is on its way."]
        [:p {:class "mt-1 text-sm text-gray-600"} "We'll be in touch as soon as we can."]]
       [:div {:class "mt-6"} (contact-form ctx)])]
    [:div {:class "lg:col-span-2 space-y-4"}
     [:div {:class "rounded-2xl bg-gray-50 p-6 ring-1 ring-gray-900/5"}
      [:h3 {:class "text-sm font-semibold uppercase tracking-wide text-gray-500"} "Email"]
      [:p {:class "mt-2 text-sm"} [:a {:href "mailto:info@tmtours.co.za" :class "text-brand-600 hover:text-brand-800"} "info@tmtours.co.za"]]
      [:p {:class "mt-1 text-sm"} [:a {:href "mailto:mafika@tmtours.co.za" :class "text-brand-600 hover:text-brand-800"} "mafika@tmtours.co.za"]]]
     [:div {:class "rounded-2xl bg-gray-50 p-6 ring-1 ring-gray-900/5"}
      [:h3 {:class "text-sm font-semibold uppercase tracking-wide text-gray-500"} "Phone"]
      [:p {:class "mt-2 text-sm text-gray-700"} "Globe Road: (033) 386 9139"]
      [:p {:class "mt-1 text-sm text-gray-700"} "New England Road: (033) 346 0177"]]
     [:div {:class "rounded-2xl bg-gray-50 p-6 ring-1 ring-gray-900/5"}
      [:h3 {:class "text-sm font-semibold uppercase tracking-wide text-gray-500"} "Follow Us"]
      [:p {:class "mt-2 text-sm text-gray-700"} "Facebook & Twitter: @tmtours"]
      [:p {:class "mt-1 text-sm text-gray-700"} "Skype: tmtours"]]]]])

(defn- directions-list [items]
  [:ul {:class "mt-2 list-none pl-0 my-0 space-y-1 text-sm text-gray-600"}
   (map-indexed (fn [i step] [:li (str (inc i) ". " step)]) items)])

(defn- location-card [{:keys [name address phone alt-phone fax directions-jhb directions-durban]}]
  [:div {:class "rounded-3xl bg-gray-50 p-6 ring-1 ring-gray-900/5"}
   [:div {:class "flex items-start gap-3"}
    (icons/pin-icon)
    [:div
     [:h3 {:class "text-lg font-bold text-gray-900"} name]
     [:p {:class "mt-1 text-sm text-gray-600"} address]]]
   [:dl {:class "mt-4 space-y-1 text-sm text-gray-700"}
    [:div [:span {:class "font-semibold"} "Tel: "] phone]
    (when alt-phone [:div [:span {:class "font-semibold"} "Alt: "] alt-phone])
    (when fax [:div [:span {:class "font-semibold"} "Fax: "] fax])]
   [:details {:class "mt-4 group"}
    [:summary {:class "cursor-pointer text-sm font-semibold text-brand-600 hover:text-brand-800"}
     "Directions from Johannesburg / Durban"]
    [:div {:class "mt-3 grid gap-4 sm:grid-cols-2"}
     [:div
      [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "From Johannesburg"]
      (directions-list directions-jhb)]
     [:div
      [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "From Durban"]
      (directions-list directions-durban)]]]])

(defn- locations-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16"}
    [:p {:class "text-sm font-semibold uppercase tracking-wide text-brand-600"} "Find Us"]
    [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Our Two Locations"]
    [:div {:class "mt-8 grid gap-6 md:grid-cols-2"}
     (map location-card locations)]]])

(defn contact-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
   [:main
    (shared/hero-section
     {:badge "We'd Love to Hear From You"
      :title "Get in Touch"
      :description "Questions about a room, a tour, or a reservation? Send us a message and we'll get back to you."})
    (form-section ctx)
    (locations-section)]
   (footer/footer)))

(defn- contact-email [{:keys [name email category subject message]}]
  {:to [{:email "info@tmtours.co.za"}]
   :subject (str "Website enquiry (" category "): " (if (seq subject) subject name))
   :html (rum/render-static-markup
          [:div
           [:p [:b "Name: "] name]
           [:p [:b "Email: "] email]
           [:p [:b "Category: "] category]
           [:p [:b "Subject: "] subject]
           [:p [:b "Message:"]]
           [:p message]])
   :text (str "Name: " name "\n"
              "Email: " email "\n"
              "Category: " category "\n"
              "Subject: " subject "\n\n"
              message)})

(defn send-message [{:keys [params] :as ctx}]
  (let [{:keys [name email message]} params]
    (if (and (seq name) (seq email) (seq message))
      (if (email/send-email ctx (contact-email params))
        {:status 303 :headers {"location" "/contact?sent=true"}}
        {:status 303 :headers {"location" "/contact?error=send-failed"}})
      {:status 303 :headers {"location" "/contact?error=missing-fields"}})))

(def module
  {:routes [["/contact" {:get contact-page}]
            ["/contact/send" {:post send-message}]]})
