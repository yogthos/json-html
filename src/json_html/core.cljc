(ns json-html.core
  #?(:clj
     (:require
       [clojure.data.json :as json]
       [clojure.string :as st]
       [json-html.html :refer [html escape-html]])
     :cljs
     (:require
      [clojure.string :as st]
      [json-html.html :refer [html]]))
  #?(:clj
     (:import
      [clojure.lang IPersistentMap IPersistentSet IPersistentCollection Keyword Symbol])))

(defn render-keyword [k]
  (str ":" (->> k ((juxt namespace name)) (remove nil?) (st/join "/"))))

(defn str-compare [k1 k2]
  (compare (str k1) (str k2)))

(defn sort-map [m]
  #?(:clj  (try
             (into (sorted-map) m)
             (catch Exception _
               (into (sorted-map-by str-compare) m)))
     :cljs (let [m (mapv (fn [[k v]]
                           [(if (= (type k) js/Object) (js->clj k) k) v])
                         m)]
             (try
               (into (sorted-map) m)
               (catch js/Error _
                 (into (sorted-map-by str-compare) m))))))

(defn sort-set [s]
  (try
    (into (sorted-set) s)
    (catch #?(:clj Exception :cljs js/Error) _
      (into (sorted-set-by str-compare) s))))

(def url-regex                                         ;; good enough...
  (re-pattern "(\\b(https?|ftp|file|ldap)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|])"))

(defn linkify-links
  "Make links clickable."
  [string]
  (clojure.string/replace string url-regex "<a class='jh-type-string-link' href=$1>$1</a>"))

(defprotocol Render
  (render [this] "Renders the element a Hiccup structure"))

#?(:clj
   (do
     (extend-protocol Render
       nil
       (render [_] [:span.jh-empty nil])

       java.util.Date
       (render [this]
         [:span.jh-type-date
          (.format (java.text.SimpleDateFormat. "MMM dd, yyyy HH:mm:ss") this)])

       Character
       (render [this] [:span.jh-type-string (str this)])

       Boolean
       (render [this] [:span.jh-type-bool this])

       Integer
       (render [this] [:span.jh-type-number this])

       Double
       (render [this] [:span.jh-type-number this])

       Long
       (render [this] [:span.jh-type-number this])

       Float
       (render [this] [:span.jh-type-number this])

       Keyword
       (render [this] [:span.jh-type-string (render-keyword this)])

       Symbol
       (render [this] [:span.jh-type-string (str this)])

       String
       (render [this] [:span.jh-type-string
                       (if (.isEmpty (.trim this))
                         [:span.jh-empty-string]
                         (escape-html this))])

       IPersistentMap
       (render [this]
         (if (empty? this)
           [:div.jh-type-object [:span.jh-empty-map]]
           [:table.jh-type-object
            [:tbody
             (for [[k v] (sort-map this)]
               [:tr [:th.jh-key.jh-object-key (render k)]
                [:td.jh-value.jh-object-value (render v)]])]]))

       IPersistentSet
       (render [this]
         (if (empty? this)
           [:div.jh-type-set [:span.jh-empty-set]]
           [:ul (for [item (sort-set this)] [:li.jh-value (render item)])]))

       IPersistentCollection
       (render [this]
         (if (empty? this)
           [:div.jh-type-object [:span.jh-empty-collection]]
           [:table.jh-type-object
            [:tbody
             (for [[i v] (map-indexed vector this)]
               [:tr [:th.jh-key.jh-array-key i]
                [:td.jh-value.jh-array-value (render v)]])]]))

       Object
       (render [this]
         [:span.jh-type-string (.toString this)]))

     (defn edn->html [edn]
       (-> (html
            [:div.jh-root
             (render edn)])
           (linkify-links)))

     (defn json->html [json]
       (-> json json/read-str edn->html))))

#?(:cljs
   (do
     (defn escape-html [s]
       (st/escape s
                  {"&" "&amp;"
                   ">" "&gt;"
                   "<" "&lt;"
                   "\"" "&quot;"}))

     (defn- obj->clj [obj]
       (reduce
        (fn [props k]
          (assoc props (keyword k) (aget obj k)))
        {}
        (js/Object.keys obj)))

     (declare render-html)

     (defn render-collection [col]
       (if (empty? col)
         [:div.jh-type-object [:span.jh-empty-collection]]
         [:table.jh-type-object
          [:tbody
           (for [[i v] (map-indexed vector col)]
             ^{:key i} [:tr [:th.jh-key.jh-array-key i]
                        [:td.jh-value.jh-array-value (render-html v)]])]]))

     (defn render-set [s]
       (if (empty? s)
         [:div.jh-type-set [:span.jh-empty-set]]
         [:ul (for [item (sort-set s)] [:li.jh-value (render-html item)])]))

     (defn render-map [m]
       (if (empty? m)
         [:div.jh-type-object [:span.jh-empty-map]]
         [:table.jh-type-object
          [:tbody
           (for [[k v] (sort-map m)]
             ^{:key k} [:tr [:th.jh-key.jh-object-key (render-html k)]
                        [:td.jh-value.jh-object-value (render-html v)]])]]))

     (defn render-string [s]
       [:span.jh-type-string
        (if (st/blank? s)
          [:span.jh-empty-string]
          (escape-html s))])

     (defn render-html [v]
       (let [t (type v)]
         (cond
           (satisfies? Render v) (render v)
           (= t Keyword) [:span.jh-type-string (render-keyword v)]
           (= t Symbol) [:span.jh-type-string (str v)]
           (= t js/String) [:span.jh-type-string (render-string v)]
           (= t js/Date) [:span.jh-type-date (.toString v)]
           (= t js/Boolean) [:span.jh-type-bool (str v)]
           (= t js/Number) [:span.jh-type-number v]
           (= t js/Array) (render-html (js->clj v))
           (satisfies? IMap v) (render-map v)
           (satisfies? ISet v) (render-set v)
           (satisfies? ICollection v) (render-collection v)
           (= t js/Object) (render-html (obj->clj v))
           nil [:span.jh-empty nil])))

     (defn edn->hiccup [edn]
       [:div.jh-root (render-html edn)])

     (defn edn->html [edn]
       (linkify-links (html (edn->hiccup edn))))

     (defn json->hiccup [json]
       [:div.jh-root (render-html (js->clj json))])

     (defn json->html [json]
       (linkify-links (html (json->hiccup json))))))
