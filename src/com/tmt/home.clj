(ns com.tmt.home
  (:require [com.biffweb :as biff]
            [com.tmt.middleware :as mid]
            [com.tmt.ui :as ui]
            [com.tmt.ui.components.nav :as nav]
            [com.tmt.ui.components.footer :as footer]
            [com.tmt.settings :as settings]))

(def email-disabled-notice
  [:.text-sm.mt-3.bg-blue-100.rounded.p-2
   "Until you add API keys for MailerSend and reCAPTCHA, we'll print your sign-up "
   "link to the console. See config.edn."])

(defn- arrow-icon []
  [:svg {:viewBox "0 0 20 20"
         :xmlns "http://www.w3.org/2000/svg"
         :aria-hidden "true"
         :focusable "false"
         :class "size-4 transition-transform duration-200 group-hover:translate-x-0.5"
         :style {:display "inline" :fill "none" :stroke "currentcolor" :stroke-width "2"}}
   [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M4.5 10h11m0 0-4-4m4 4-4 4"}]])

(defn- image-placeholder [label]
  [:div {:class (str "aspect-video w-full rounded-3xl ring-1 ring-gray-900/5 "
                     "bg-gradient-to-br from-gray-100 to-gray-200 "
                     "flex items-center justify-center text-gray-400 text-sm")}
   label])

(defn- hero-section []
  [:section#cta-arrow-region
   {:class "relative isolate overflow-hidden bg-gradient-to-br from-blue-900 to-blue-700 text-white"}
   [:canvas#cta-arrow-canvas {:class "absolute inset-0 pointer-events-none z-40"}]
   [:div {:aria-hidden "true" :class "absolute inset-0 -z-10"}
    [:div {:class "absolute -top-24 -left-24 size-96 rounded-full bg-blue-500 opacity-30 blur-3xl"}]
    [:div {:class "absolute -bottom-24 -right-24 size-96 rounded-full bg-indigo-400 opacity-20 blur-3xl"}]]
   [:div {:class "max-w-6xl mx-auto px-8 py-24 text-center"}
    [:p {:class "text-sm font-semibold uppercase tracking-wide text-blue-200"}
     "Pietermaritzburg, KwaZulu-Natal"]
    [:h1 {:class "mt-3 text-balance text-4xl sm:text-5xl font-bold"}
     "One Lodge, Two Locations"]
    [:p {:class "mt-4 text-pretty text-lg text-blue-100 max-w-2xl mx-auto"}
     "Facilities to make our guests feel like royalty and we guarantee a memorable stay."]
    [:div {:class "mt-8 flex flex-col sm:flex-row items-center justify-center gap-4"}
     [:a#cta-arrow-target
      {:href "/reservations"
       :class (str "group inline-flex items-center gap-1.5 rounded-full bg-white text-blue-800 "
                   "px-6 py-3 text-sm font-semibold shadow-sm transition-colors hover:bg-blue-50 "
                   "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-white "
                   "focus-visible:ring-offset-2 focus-visible:ring-offset-blue-800")}
      "Book Your Stay"
      (arrow-icon)]
     [:a {:href "/tours"
          :class (str "rounded-full ring-1 ring-inset ring-white/60 px-6 py-3 text-sm font-semibold "
                      "transition-colors hover:bg-white/10 focus-visible:outline-none "
                      "focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-offset-2 "
                      "focus-visible:ring-offset-blue-800")}
      "Explore Tours"]]]])

(defn- cta-banner [title href]
  [:a {:href href
       :class (str "group block rounded-2xl bg-white p-6 text-center ring-1 ring-gray-900/5 shadow-sm "
                   "transition-all duration-200 hover:-translate-y-1 hover:shadow-lg hover:ring-blue-600/20")}
   [:span {:class "inline-flex items-center gap-1.5 text-base font-semibold text-gray-900"}
    title
    (arrow-icon)]])

(defn- cta-banners []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-12 grid gap-6 sm:grid-cols-3"}
    (cta-banner "Make a Reservation" "/reservations")
    (cta-banner "View the Rooms" "/rooms")
    (cta-banner "Latest Specials" "/specials")]])

(defn- read-more [href]
  [:a {:href href
       :class (str "group mt-4 inline-flex items-center gap-1.5 text-sm font-semibold "
                   "text-blue-600 hover:text-blue-800")}
   "Read More"
   (arrow-icon)])

(defn- welcome-section []
  [:section {:class "bg-gray-50"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-2 items-center"}
    (image-placeholder "Lodge photo")
    [:div
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-blue-600"} "Welcome"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Welcome to TM Guest Lodge"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      (str "TM Guest Lodge, offering guests two locations from which to opt for, Globe Road and "
           "New England Road situated in strategic locations in Scottsville, Pietermaritzburg "
           "which are ideally suited for individuals, executives and family travel.")]
     (read-more "/about")]]])

(defn- tours-section []
  [:section {:class "bg-white"}
   [:div {:class "max-w-6xl mx-auto px-8 py-16 grid gap-10 md:grid-cols-2 items-center"}
    [:div {:class "order-2 md:order-1"}
     [:p {:class "text-sm font-semibold uppercase tracking-wide text-blue-600"} "Tours"]
     [:h2 {:class "mt-2 text-balance text-3xl font-bold text-gray-900"} "Travel with TM Tours"]
     [:p {:class "mt-4 text-pretty text-gray-600 leading-relaxed"}
      (str "The tour service started on a small scale as a response to a need to ferry school "
           "children from Imbali and Edendale to schools in the city. The service is beneficial "
           "to small, medium and large business enterprises as well as public institutions.")]
     (read-more "/tours")]
    [:div {:class "order-1 md:order-2"}
     (image-placeholder "Tours photo")]]])

(defn home-page [ctx]
  (ui/base
   ctx
   (nav/navbar)
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
