(ns com.tmt.reservations
  (:require [com.biffweb :as biff]
            [com.tmt.email :as email]
            [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.components.shared :as shared]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]
            [rum.core :as rum]
            [clojure.string :as str]
            [clojure.tools.logging :as log]))

(def ^:private rooms
  [{:key "single-double" :name "Single / Double Room" :rate "R 380" :bed "King bed"
    :amenities ["Flat-screen TV" "En-suite bathroom" "Bar fridge" "Microwave" "Tea/coffee facility"]
    :desc "Comfortable room with a king bed, ideal for solo travellers or couples."}
   {:key "twin" :name "Twin Room" :rate "R 440" :bed "Two single beds"
    :amenities ["Flat-screen TV" "En-suite bathroom" "Bar fridge" "Microwave" "Tea/coffee facility"]
    :desc "Perfect for friends or colleagues travelling together."}
   {:key "self-catering" :name "Self Catering" :rate "R 450" :bed "King or single beds"
    :amenities ["Flat-screen TV" "Stove & oven" "Cutlery & crockery" "Microwave" "Bar fridge"]
    :desc "Fully equipped kitchenette for longer stays."}
   {:key "cottage" :name "Cottage / Family Room" :rate "R 480" :bed "King bed and single beds"
    :capacity "Sleeps up to 6"
    :amenities ["Flat-screen TV" "Stove & oven" "Cutlery & crockery" "Microwave" "Bar fridge"]
    :desc "Spacious room ideal for families or small groups."}])

(def ^:private locations
  [{:key "globe" :name "Globe Road" :address "18 Globe Road, Scottsville, Pietermaritzburg"
    :phone "(033) 386 9139" :email "info@tmtours.co.za"}
   {:key "new-england" :name "New England Road" :address "69 New England Road, Scottsville, Pietermaritzburg"
    :phone "(033) 346 0177" :alt-phone "(033) 346 0957" :email "info@tmtours.co.za"}])

(def ^:private steps
  [{:num 1 :label "Choose Room"}
   {:num 2 :label "Dates & Guests"}
   {:num 3 :label "Your Details"}
   {:num 4 :label "Review & Confirm"}])

(defn- step-indicator [current-step]
  [:div {:class "max-w-3xl mx-auto px-8 pt-12"}
   [:div {:class "flex items-center justify-between"}
    (for [s steps]
      (let [n (:num s)
            active? (= n current-step)
            done? (< n current-step)]
        [:div {:class "flex items-center gap-2"}
         [:div {:class (str "flex size-10 shrink-0 items-center justify-center rounded-full text-sm font-bold "
                            (cond active? "bg-brand-600 text-white shadow-md"
                                  done? "bg-brand-100 text-brand-700"
                                  :else "bg-gray-100 text-gray-400"))}
          (if done? (icons/check-icon) (str n))]
         [:span {:class (str "hidden sm:block text-sm font-medium "
                             (cond active? "text-brand-700"
                                   done? "text-brand-600"
                                   :else "text-gray-400"))}
          (:label s)]
         (when (< n 4)
           [:div {:class (str "hidden sm:block h-px w-12 md:w-20 "
                              (if done? "bg-brand-400" "bg-gray-200"))}])]))]])


(defn- step-1-room-card [{:keys [key name rate bed capacity amenities desc]} selected-key]
  [:label {:class "relative flex cursor-pointer rounded-2xl border-2 p-5 transition-all border-gray-200 bg-white hover:border-brand-300 hover:shadow-sm has-[:checked]:border-brand-500 has-[:checked]:bg-brand-50 has-[:checked]:shadow-md"}
   [:input {:type "radio" :name "room" :value key :class "sr-only peer"
            :checked (when (= key selected-key) "checked")}]
   [:div {:class "flex flex-1 flex-col gap-2"}
    [:div {:class "flex items-start justify-between"}
     [:h3 {:class "text-base font-bold text-gray-900"} name]
     [:span {:class "shrink-0 rounded-full px-3 py-1 text-sm font-bold bg-brand-100 text-brand-700 peer-checked:bg-brand-600 peer-checked:text-white"}
      rate]]
    [:p {:class "text-sm text-gray-500"} desc]
    [:div {:class "flex flex-wrap gap-x-4 gap-y-1 text-xs text-gray-500"}
     [:span {:class "flex items-center gap-1"} (icons/bed-icon) bed]
     (when capacity [:span {:class "flex items-center gap-1"} (icons/users-icon) capacity])]
    [:div {:class "mt-1 flex flex-wrap gap-1.5 opacity-60 peer-checked:opacity-100"}
     (for [a amenities]
       [:span {:class "rounded-md bg-gray-100 px-2 py-0.5 text-xs text-gray-600"} a])]
    [:span {:class "mt-1 text-xs font-semibold text-brand-600 hidden peer-checked:block"} "Selected"]]])

(defn- step-1 [{:keys [room location]}]
  [:div
   [:div {:class "text-center mb-10"}
    [:h2 {:class "text-2xl font-bold text-gray-900"} "Choose Your Room"]
    [:p {:class "mt-2 text-gray-600"} "Select a room type and preferred location."]]
   [:div {:class "grid gap-4 sm:grid-cols-2"}
    (for [r rooms]
      (step-1-room-card r room))]
   [:div {:class "mt-8"}
    [:label {:class "block text-sm font-medium text-gray-700"} "Preferred Location"]
     [:div {:class "mt-2 grid gap-3 sm:grid-cols-2"}
      (for [l locations]
        [:label {:class "flex cursor-pointer items-center gap-3 rounded-xl border-2 p-4 transition-all border-gray-200 bg-white hover:border-brand-300 has-[:checked]:border-brand-500 has-[:checked]:bg-brand-50"}
         [:input {:type "radio" :name "location" :value (:key l) :class "sr-only peer"
                  :checked (when (= (:key l) location) "checked")}]
         [:div
          [:p {:class "text-sm font-semibold text-gray-900"} (:name l)]
          [:p {:class "text-xs text-gray-500"} (:address l)]]
         [:span {:class "ml-auto text-brand-600 hidden peer-checked:block"} (icons/check-icon)]])]]

   [:div {:class "mt-10 text-center"}
    [:button {:type "submit" :name "step" :value "1"
              :class (str "inline-flex items-center gap-2 rounded-full bg-brand-600 px-10 py-3 "
                          "text-sm font-semibold text-white shadow-md "
                          "transition-all hover:bg-brand-500 hover:shadow-lg")}
     "Continue" (icons/arrow-icon)]]])

(defn- step-2 [{:keys [check-in check-out guests]}]
  [:div
   [:div {:class "text-center mb-10"}
    [:h2 {:class "text-2xl font-bold text-gray-900"} "Dates & Guests"]
    [:p {:class "mt-2 text-gray-600"} "When are you planning to stay?"]]
   [:div {:class "mx-auto max-w-lg space-y-6"}
    [:div {:class "grid gap-5 sm:grid-cols-2"}
     [:div
      [:label {:for "check-in" :class "block text-sm font-medium text-gray-700"} "Check-in Date"]
      [:input {:id "check-in" :name "check-in" :type "date" :required true :value (or check-in "")
               :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                           "text-gray-900 shadow-neu-inset focus:shadow-neu")}]]
     [:div
      [:label {:for "check-out" :class "block text-sm font-medium text-gray-700"} "Check-out Date"]
      [:input {:id "check-out" :name "check-out" :type "date" :required true :value (or check-out "")
               :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                           "text-gray-900 shadow-neu-inset focus:shadow-neu")}]]]
    [:div
     [:label {:for "guests" :class "block text-sm font-medium text-gray-700"} "Number of Guests"]
     [:select {:id "guests" :name "guests" :required true
               :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                           "text-gray-900 shadow-neu-inset focus:shadow-neu")}
      (for [n (range 1 11)]
        [:option {:value n :selected (when (= (str n) guests) "selected")}
         (str n " guest" (when (> n 1) "s"))])]]]
   [:div {:class "mt-10 text-center"}
    [:button {:type "submit" :name "step" :value "2"
              :class (str "inline-flex items-center gap-2 rounded-full bg-brand-600 px-10 py-3 "
                          "text-sm font-semibold text-white shadow-md "
                          "transition-all hover:bg-brand-500 hover:shadow-lg")}
     "Continue" (icons/arrow-icon)]]])

(defn- step-3 [{:keys [name email phone requests]}]
  [:div
   [:div {:class "text-center mb-10"}
    [:h2 {:class "text-2xl font-bold text-gray-900"} "Your Details"]
    [:p {:class "mt-2 text-gray-600"} "How can we reach you?"]]
   [:div {:class "mx-auto max-w-lg space-y-5"}
    [:div {:class "grid gap-5 sm:grid-cols-2"}
     [:div
      [:label {:for "name" :class "block text-sm font-medium text-gray-700"} "Full Name"]
      [:input {:id "name" :name "name" :type "text" :required true :value (or name "")
               :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                           "text-gray-900 shadow-neu-inset placeholder:text-gray-400 focus:shadow-neu")}]]
     [:div
      [:label {:for "email" :class "block text-sm font-medium text-gray-700"} "Email"]
      [:input {:id "email" :name "email" :type "email" :required true :value (or email "")
               :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                           "text-gray-900 shadow-neu-inset placeholder:text-gray-400 focus:shadow-neu")}]]]
    [:div
     [:label {:for "phone" :class "block text-sm font-medium text-gray-700"} "Phone Number"]
     [:input {:id "phone" :name "phone" :type "tel" :required true :value (or phone "")
              :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                          "text-gray-900 shadow-neu-inset placeholder:text-gray-400 focus:shadow-neu")}]]
    [:div
     [:label {:for "requests" :class "block text-sm font-medium text-gray-700"} "Special Requests"]
     [:textarea {:id "requests" :name "requests" :rows 3 :value (or requests "")
                 :placeholder "Any special requirements, preferences or questions..."
                 :class (str "mt-1.5 block w-full rounded-lg border-0 bg-white px-3.5 py-2.5 text-sm "
                             "text-gray-900 shadow-neu-inset placeholder:text-gray-400 "
                             "focus:shadow-neu")}]
      [:p {:class "mt-1 text-xs text-gray-400"} "Optional"]]]
    [:div {:class "mt-10 text-center"}
     [:button {:type "submit" :name "step" :value "3"
               :class (str "inline-flex items-center gap-2 rounded-full bg-brand-600 px-10 py-3 "
                           "text-sm font-semibold text-white shadow-md "
                           "transition-all hover:bg-brand-500 hover:shadow-lg")}
      "Continue" (icons/arrow-icon)]]])

(defn- step-4 [{:keys [room location check-in check-out guests name email phone requests]}]
  (let [room-name (some #(when (= (:key %) room) (:name %)) rooms)
        room-rate (some #(when (= (:key %) room) (:rate %)) rooms)
        loc-name (some #(when (= (:key %) location) (:name %)) locations)]
    [:div
     [:div {:class "text-center mb-10"}
      [:h2 {:class "text-2xl font-bold text-gray-900"} "Review Your Booking"]
      [:p {:class "mt-2 text-gray-600"} "Please check everything looks right before confirming."]]
     [:div {:class "mx-auto max-w-2xl space-y-4"}
      [:div {:class "rounded-2xl bg-white border border-gray-200 divide-y divide-gray-100"}
       [:div {:class "flex items-center justify-between p-5"}
        [:div
         [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Room"]
         [:p {:class "mt-1 text-base font-bold text-gray-900"} room-name]]
        [:span {:class "rounded-full bg-brand-100 px-3 py-1 text-sm font-bold text-brand-700"}
         room-rate]]
       [:div {:class "flex items-center justify-between p-5"}
        [:div
         [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Location"]
         [:p {:class "mt-1 text-base font-bold text-gray-900"} loc-name]]]
       [:div {:class "flex items-center justify-between p-5"}
        [:div
         [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Check-in"]
         [:p {:class "mt-1 text-base font-bold text-gray-900"} check-in]]
        [:div {:class "text-right"}
         [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Check-out"]
         [:p {:class "mt-1 text-base font-bold text-gray-900"} check-out]]]
       [:div {:class "flex items-center justify-between p-5 bg-gray-50"}
        [:div
         [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Guests"]
         [:p {:class "mt-1 text-base font-bold text-gray-900"} guests]]
        [:div {:class "text-right"}
         [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Name"]
         [:p {:class "mt-1 text-base font-bold text-gray-900"} name]]]
       [:div {:class "p-5"}
        [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Email"]
        [:p {:class "mt-1 text-base font-bold text-gray-900"} email]
        [:p {:class "mt-2 text-xs font-semibold uppercase tracking-wide text-gray-500"} "Phone"]
        [:p {:class "mt-1 text-sm text-gray-700"} phone]]
       (when (seq requests)
         [:div {:class "p-5"}
          [:p {:class "text-xs font-semibold uppercase tracking-wide text-gray-500"} "Special Requests"]
          [:p {:class "mt-1 text-sm text-gray-700"} requests]])]]
     [:div {:class "mt-10 text-center"}
      [:button {:type "submit" :name "step" :value "4"
                :class (str "inline-flex items-center gap-2 rounded-full bg-green-600 px-12 py-3.5 "
                            "text-base font-semibold text-white shadow-md "
                            "transition-all hover:bg-green-500 hover:shadow-lg")}
       (icons/check-icon) "Confirm Booking"]]
     [:div {:class "mt-4 text-center"}
      [:p {:class "text-xs text-gray-400"}
       "By confirming, you agree to our booking terms. We will be in touch within 24 hours."]]]))

(defn- success-section []
  [:div {:class "max-w-6xl mx-auto px-8 py-24"}
   [:div {:class "mx-auto max-w-lg text-center"}
    [:div {:class "mx-auto flex size-16 items-center justify-center rounded-full bg-green-100"}
     [:span {:class "text-3xl text-green-600"} (icons/check-icon)]]
    [:h2 {:class "mt-6 text-2xl font-bold text-gray-900"} "Booking Request Sent!"]
    [:p {:class "mt-3 text-gray-600 leading-relaxed"}
     "Thanks for your booking request. We will check availability and confirm your reservation within 24 hours. "
     "If you need immediate assistance, call us at"]
    [:p {:class "mt-2 font-semibold text-brand-700"} "(033) 386 9139"]
    [:div {:class "mt-8 flex flex-col sm:flex-row items-center justify-center gap-4"}
     (shared/primary-button "Back to Home" {:href "/"})
      (shared/secondary-button "View Rooms" {:href "/rooms"})]]])

(defn- hidden-inputs [params ks]
  (for [k ks
        :let [v (get params k)]
        :when (seq v)]
    [:input {:type "hidden" :name (name k) :value v}]))

(defn- wizard-section [ctx]
  (let [params (:params ctx)
        step (or (some-> params :step parse-long) 1)]
    [:section {:class "bg-gray-50 min-h-screen"}
     (step-indicator step)
     [:div {:class "max-w-4xl mx-auto px-8 py-12"}
      (biff/form
       {:action "/reservations" :class "rounded-2xl bg-white p-6 sm:p-10 shadow-neu transition-all duration-300"}
       (case step
         1 (step-1 params)
         2 [:<> (step-2 params)
            (hidden-inputs params [:room :location])]
         3 [:<> (step-3 params)
            (hidden-inputs params [:room :location :check-in :check-out :guests])]
         4 [:<> (step-4 (assoc params :error (get-in ctx [:params :error])))
            (hidden-inputs params [:room :location :check-in :check-out :guests :name :email :phone :requests])])
       (case step
          (1 2 3) [:div {:class "mt-8 text-center"}
                   [:a {:href (if (= step 1) "/rooms" (str "/reservations?step=" (dec step)))
                        :class "text-sm text-gray-500 hover:text-gray-700 transition-colors"}
                    (if (= step 1) "Browse all rooms" "Back")]]
          4 [:div {:class "mt-8 text-center"}
               [:a {:href "/reservations?step=3"
                    :class "text-sm text-gray-500 hover:text-gray-700 transition-colors"}
                "Back"]])
        (when-some [error (:error params)]
         [:<> (when (= step 4)
                [:div {:class "mx-auto mt-4 max-w-md text-center text-sm text-red-600"}
                 (case error
                   "send-failed" "Something went wrong. Please try again or call us directly."
                   "There was an error.")])]))]]))

(defn- quick-links-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 md:py-24"}
    (shared/section-heading "Prefer to Call?" "Talk to Us Directly")
    [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed max-w-2xl"}
     "Give us a call, send a WhatsApp, or drop by. We are always happy to help you plan your stay."]
    [:div {:class "mt-10 grid gap-6 md:grid-cols-2"}
     (for [l locations]
       [:div {:class (str "rounded-2xl bg-white p-6 "
                          "shadow-neu transition-all duration-300 "
                          "hover:-translate-y-1 hover:shadow-neu-hover")}
        [:div {:class "flex items-start gap-3"}
         (icons/pin-icon)
         [:div
          [:h3 {:class "text-lg font-bold text-gray-900"} (:name l)]
          [:p {:class "mt-1 text-sm text-gray-600"} (:address l)]]]
        [:dl {:class "mt-4 space-y-1 text-sm text-gray-700"}
         [:div {:class "flex items-center gap-2"}
          (icons/phone-icon)
          [:span [:a {:href (str "tel:" (clojure.string/replace (:phone l) " " ""))
                      :class "text-brand-600 hover:text-brand-800"} (:phone l)]]]
         (when (:alt-phone l)
           [:div {:class "flex items-center gap-2"}
            (icons/phone-icon)
            [:span [:a {:href (str "tel:" (clojure.string/replace (:alt-phone l) " " ""))
                        :class "text-brand-600 hover:text-brand-800"} (:alt-phone l)]]])]
         [:div {:class "mt-4 flex flex-wrap gap-3 pt-4 border-t border-gray-100"}
          [:a {:href (str "tel:" (clojure.string/replace (:phone l) " " ""))
               :class (str "inline-flex items-center gap-1.5 rounded-full bg-brand-600 px-4 py-2 "
                           "text-xs font-semibold text-white transition-colors hover:bg-brand-500")}
           (icons/phone-icon) "Call"]
          [:a {:href "https://wa.me/27333869139" :target "_blank" :rel "noopener noreferrer"
               :class (str "inline-flex items-center gap-1.5 rounded-full bg-green-500 px-4 py-2 "
                           "text-xs font-semibold text-white transition-colors hover:bg-green-600")}
           "WhatsApp"]
          [:a {:href (str "mailto:" (:email l))
               :class (str "group inline-flex items-center gap-1.5 text-xs font-semibold "
                           "text-brand-600 hover:text-brand-800")}
           (icons/mail-icon) "Email"]]])]]])

(defn reservations-page [ctx]
  (let [sent (get-in ctx [:params :sent])]
    (if (= sent "true")
      (ui/base
       ctx
       (nav/navbar ctx)
       [:main (success-section)]
       (footer/footer))
      (ui/base
       ctx
       (nav/navbar ctx)
       [:main
        (shared/hero-section
         {:badge "Book Your Stay"
          :title "Make a Reservation"
          :description "Follow the steps below and we will confirm availability within 24 hours."
          :py "py-16 md:py-20"})
        (wizard-section ctx)
        (quick-links-section)]
       (footer/footer)))))

(defn- booking-email [{:keys [name email phone room location check-in check-out guests requests]}]
  (let [room-name (some #(when (= (:key %) room) (:name %)) rooms)
        loc-name (some #(when (= (:key %) location) (:name %)) locations)]
    {:to [{:email "info@tmtours.co.za"}]
     :subject (str "Booking request: " name " - " room-name)
     :html (rum/render-static-markup
            [:div
             [:p [:b "Name: "] name]
             [:p [:b "Email: "] email]
             [:p [:b "Phone: "] phone]
             [:p [:b "Location: "] loc-name]
             [:p [:b "Room: "] room-name]
             [:p [:b "Check-in: "] check-in]
             [:p [:b "Check-out: "] check-out]
             [:p [:b "Guests: "] guests]
             [:p [:b "Special Requests:"]]
             [:p (or requests "None")]])
     :text (str "Name: " name "\n"
                "Email: " email "\n"
                "Phone: " phone "\n"
                "Location: " loc-name "\n"
                "Room: " room-name "\n"
                "Check-in: " check-in "\n"
                "Check-out: " check-out "\n"
                "Guests: " guests "\n\n"
                "Special Requests:\n" (or requests "None"))}))

(defn- qs [m]
  (str/join "&" (for [[k v] m :when (seq v)]
                  (str (name k) "=" (java.net.URLEncoder/encode v "UTF-8")))))

(defn send-booking [{:keys [params] :as ctx}]
  (let [step (or (some-> params :step parse-long) 1)
        {:keys [name email phone room location check-in check-out guests requests]} params
        base-params {:room room :location location :check-in check-in :check-out check-out
                     :guests guests :name name :email email :phone phone :requests requests}]
    (case step
      1 (let [missing (remove seq [room location])]
          (if (empty? missing)
            {:status 303 :headers {"location" (str "/reservations?step=2&" (qs (select-keys base-params [:room :location])))}}
            {:status 303 :headers {"location" (str "/reservations?step=1&error=missing-fields&" (qs base-params))}}))
      2 (let [missing (remove seq [check-in check-out guests])]
          (if (empty? missing)
            {:status 303 :headers {"location" (str "/reservations?step=3&" (qs base-params))}}
            {:status 303 :headers {"location" (str "/reservations?step=2&error=missing-fields&" (qs base-params))}}))
      3 (let [missing (remove seq [name email phone])]
          (if (empty? missing)
            {:status 303 :headers {"location" (str "/reservations?step=4&" (qs base-params))}}
            {:status 303 :headers {"location" (str "/reservations?step=3&error=missing-fields&" (qs base-params))}}))
4 (if (and (seq name) (seq email) (seq phone) (seq room) (seq location) (seq check-in) (seq check-out))
           (if (email/send-email ctx (booking-email params))
             (do (log/info "Booking request from" name "<" email ">" "-" room "at" location
                           (str "(" check-in "→" check-out "," guests "guests)"))
                 {:status 303 :headers {"location" "/reservations?sent=true"}})
             {:status 303 :headers {"location" (str "/reservations?step=4&error=send-failed&" (qs base-params))}})
                       {:status 303 :headers {"location" (str "/reservations?step=4&error=missing-fields&" (qs base-params))}}))))

(def module
  {:routes [["/reservations" {:get reservations-page :post send-booking}]
            ["/reservations/send" {:post send-booking}]]})

