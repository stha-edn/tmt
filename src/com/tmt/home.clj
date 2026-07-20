(ns com.tmt.home
  (:require [com.biffweb :as biff]
            [com.tmt.middleware :as mid]
            [com.tmt.ui :as ui]
            [com.tmt.ui.icons :as icons]
            [com.tmt.ui.illustrations :as illust]
            [com.tmt.ui.components.shared :as shared]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]
            [com.tmt.settings :as settings]))

(def email-disabled-notice
  [:.text-sm.mt-3.bg-brand-100.rounded.p-2
   "Until you add API keys for MailerSend and reCAPTCHA, we'll print your sign-up "
   "link to the console. See config.edn."])




(defn- hero-section []
  (shared/hero-section
   {:id "cta-arrow-region"
    :badge "Pietermaritzburg, KwaZulu-Natal"
    :title "One Lodge, Two Locations"
    :description "Facilities to make our guests feel like royalty and we guarantee a memorable stay."
    :py "py-24"
    :before [:canvas#cta-arrow-canvas {:class "absolute inset-0 pointer-events-none z-40"}]
     :children [:div {:class "mt-8 flex flex-col sm:flex-row items-center justify-center gap-4"}
                (shared/primary-button "Book Your Stay" {:href "/reservations" :id "cta-arrow-target"} icons/house-icon)
                (shared/secondary-button "Explore Tours" {:href "/tours"} icons/pin-icon)]}))

(defn- cta-banner [title href icon-fn]
  [:a {:href href
       :class (str "group relative block rounded-2xl bg-white p-6 text-center "
                   "shadow-neu-sm "
                   "transition-all duration-300 hover:-translate-y-1 hover:shadow-neu-hover "
                   "before:absolute before:inset-x-6 before:-top-px before:h-px "
                   "before:bg-gradient-to-r before:from-transparent before:via-brand-500/60 before:to-transparent")}
   [:div {:class "mx-auto mb-3 flex size-10 items-center justify-center rounded-xl bg-brand-50 text-brand-600"}
    (icon-fn)]
   [:span {:class "inline-flex items-center gap-1.5 text-base font-semibold text-gray-900 group-hover:text-brand-700"}
    title
    (icons/arrow-icon)]])

(defn- cta-banners []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-12 md:py-16 grid gap-6 sm:grid-cols-3"}
    (cta-banner "Make a Reservation" "/reservations" icons/house-icon)
    (cta-banner "View the Rooms" "/rooms" icons/bed-icon)
    (cta-banner "Latest Specials" "/specials" icons/tag-icon)]])

(defn- read-more [href]
  [:a {:href href
       :class (str "group mt-4 inline-flex items-center gap-1.5 text-sm font-semibold "
                   "text-brand-600 hover:text-brand-700 "
                   "transition-colors duration-200")}
   "Read More"
   (icons/arrow-icon)])

(defn- welcome-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 md:py-24 grid gap-10 md:grid-cols-2 items-center"}
       (illust/welcome-illustration)
     [:div
      (shared/section-heading "Welcome" "Welcome to TM Guest Lodge")
     [:p {:class "mt-6 text-pretty text-gray-600 leading-relaxed md:text-lg"}
      (str "TM Guest Lodge, offering guests two locations from which to opt for, Globe Road and "
           "New England Road situated in strategic locations in Scottsville, Pietermaritzburg "
           "which are ideally suited for individuals, executives and family travel.")]
     (read-more "/about")]]])

(defn- tours-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 md:py-24 grid gap-10 md:grid-cols-2 items-center"}
     [:div {:class "order-2 md:order-1"}
      (shared/section-heading "Tours" "Travel with TM Tours")
     [:p {:class "mt-6 text-pretty text-gray-600 leading-relaxed md:text-lg"}
      (str "The tour service started on a small scale as a response to a need to ferry school "
           "children from Imbali and Edendale to schools in the city. The service is beneficial "
           "to small, medium and large business enterprises as well as public institutions.")]
     (read-more "/tours")]
    [:div {:class "order-1 md:order-2"}
           (illust/travel-illustration)]]])

(defn home-page [ctx]
  (ui/base
   ctx
    (nav/navbar ctx)
    [:main
    (hero-section)
    (cta-banners)
    (welcome-section)
    (tours-section)]
   (footer/footer)))

(defn link-sent [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-xl.font-bold "Check your inbox"]
   [:p "We've sent a sign-in link to " [:span.font-bold (:email params)] "."]))

(defn verify-email-page [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-2xl.font-bold (str "Sign up for " settings/app-name)]
   [:.h-3]
   (biff/form
    {:action "/auth/verify-link"
     :hidden {:token (:token params)}}
    [:div [:label {:for "email"}
           "It looks like you opened this link on a different device or browser than the one "
           "you signed up on. For verification, please enter the email you signed up with:"]]
    [:.h-3]
    [:.flex
     [:input#email {:name "email" :type "email"
                    :placeholder "Enter your email address"}]
     [:.w-3]
     [:button.btn {:type "submit"}
      "Sign in"]])
   (when-some [error (:error params)]
     [:<>
      [:.h-1]
      [:.text-sm.text-red-600
       (case error
         "incorrect-email" "Incorrect email address. Try again."
         "There was an error.")]])))

(defn signin-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (biff/form
    {:action "/auth/send-code"
     :id "signin"
     :hidden {:on-error "/signin"}}
    (biff/recaptcha-callback "submitSignin" "signin")
    [:h2.text-2xl.font-bold "Sign in to " settings/app-name]
    [:.h-3]
    [:.flex
     [:input#email {:name "email"
                    :type "email"
                    :autocomplete "email"
                    :placeholder "Enter your email address"}]
     [:.w-3]
     [:button.btn.g-recaptcha
      (merge (when site-key
               {:data-sitekey site-key
                :data-callback "submitSignin"})
             {:type "submit"})
      "Sign in"]]
    (when-some [error (:error params)]
      [:<>
       [:.h-1]
       [:.text-sm.text-red-600
        (case error
          "recaptcha" (str "You failed the recaptcha test. Try again, "
                           "and make sure you aren't blocking scripts from Google.")
          "invalid-email" "Invalid email. Try again with a different address."
          "send-failed" (str "We weren't able to send an email to that address. "
                             "If the problem persists, try another address.")
          "invalid-link" "Invalid or expired link. Sign in to get a new link."
          "not-signed-in" "You must be signed in to view that page."
          "There was an error.")]])
    [:.h-1]
    [:.text-sm "Don't have an account yet? " [:a.link {:href "/"} "Sign up"] "."]
    [:.h-3]
    biff/recaptcha-disclosure
    email-disabled-notice)))

(defn enter-code-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (biff/form
    {:action "/auth/verify-code"
     :id "code-form"
     :hidden {:email (:email params)}}
    (biff/recaptcha-callback "submitCode" "code-form")
    [:div [:label {:for "code"} "Enter the 6-digit code that we sent to "
           [:span.font-bold (:email params)]]]
    [:.h-1]
    [:.flex
     [:input#code {:name "code" :type "text"}]
     [:.w-3]
     [:button.btn.g-recaptcha
      (merge (when site-key
               {:data-sitekey site-key
                :data-callback "submitCode"})
             {:type "submit"})
      "Sign in"]])
   (when-some [error (:error params)]
     [:<>
      [:.h-1]
      [:.text-sm.text-red-600
       (case error
         "invalid-code" "Invalid code."
         "There was an error.")]])
   [:.h-3]
   (biff/form
    {:action "/auth/send-code"
     :id "signin"
     :hidden {:email (:email params)
              :on-error "/signin"}}
    (biff/recaptcha-callback "submitSignin" "signin")
    [:button.link.g-recaptcha
     (merge (when site-key
              {:data-sitekey site-key
               :data-callback "submitSignin"})
            {:type "submit"})
     "Send another code"])))

(def module
  {:routes [["" {:middleware [mid/wrap-redirect-signed-in]}
             ["/"                  {:get home-page}]]
            ["/link-sent"          {:get link-sent}]
            ["/verify-link"        {:get verify-email-page}]
            ["/signin"             {:get signin-page}]
            ["/verify-code"        {:get enter-code-page}]]})
