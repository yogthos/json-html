(ns json-html.core-test
  (:require [clojure.test :refer :all]
            [json-html.core :refer :all]))

(def json-input
  "{
  \"name\": \"json.human\",
  \"description\": \"Convert\n JSON to human readable\r HTML\",
  \"author\": \"Mariano Guerra <mariano@marianoguerra.org>\",
  \"tags\": [\"DOM\", \"HTML\", \"JSON\", \"Pretty Print\"],
  \"version\": \"0.1.0\",
  \"main\": \"json.human.js\",
  \"license\" : \"MIT\",
  \"dependencies\": {
      \"crel\": \"1.0.0\"
  },
  \"repository\": {
    \"type\": \"git\",
    \"url\": \"git://github.com/marianoguerra/json.human.js.git\"
  },
  \"bugs\": {
    \"url\": \"https://github.com/yogthos/json-html/issues\"
  },
  \"contributors\": [],
  \"config\": {
    \"what?\": \"this object is just to show some extra stuff\",
    \"how?\": [\"add json.human.js\", \"add json.human.css\", \"???\", \"profit!\"],
    \"customization?\": [\"customize the css prefix\", \"change the css file\"],
    \"integer\": 42,
    \"float\": 12.3,
    \"bool\": true,
    \"emptyString\": \"\",
    \"emptyArray\": [],
    \"emptyObject\": {},
    \"htmlEntities\": \"   <- trailing <em>   & </em> and some html   \"
  }
}")

(def expected-output-from-json
  "<div class=\"jh-root\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">author</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">Mariano Guerra &lt;mariano@marianoguerra.org&gt;</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">bugs</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">url</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\"><a class='jh-type-string-link' href=https://github.com/yogthos/json-html/issues>https://github.com/yogthos/json-html/issues</a></span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">config</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">bool</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-bool\">true</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">customization?</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">customize the css prefix</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">change the css file</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">emptyArray</span></th><td class=\"jh-value jh-object-value\"><div class=\"jh-type-object\"><span class=\"jh-empty-collection\"></span></div></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">emptyObject</span></th><td class=\"jh-value jh-object-value\"><div class=\"jh-type-object\"><span class=\"jh-empty-map\"></span></div></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">emptyString</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\"><span class=\"jh-empty-string\"></span></span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">float</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-number\">12.3</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">how?</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">add json.human.js</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">add json.human.css</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">???</span></td></tr><tr><th class=\"jh-key jh-array-key\">3</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">profit!</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">htmlEntities</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">   &lt;- trailing &lt;em&gt;   &amp; &lt;/em&gt; and some html   </span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">integer</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-number\">42</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">what?</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">this object is just to show some extra stuff</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">contributors</span></th><td class=\"jh-value jh-object-value\"><div class=\"jh-type-object\"><span class=\"jh-empty-collection\"></span></div></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">dependencies</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">crel</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">1.0.0</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">description</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">Convert\n JSON to human readable\r HTML</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">license</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">MIT</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">main</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">json.human.js</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">name</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">json.human</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">repository</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">type</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">git</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">url</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">git://github.com/marianoguerra/json.human.js.git</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">tags</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">DOM</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">HTML</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">JSON</span></td></tr><tr><th class=\"jh-key jh-array-key\">3</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">Pretty Print</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">version</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">0.1.0</span></td></tr></tbody></table></div>")

(def expected-output-from-edn
  "<div class=\"jh-root\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:date</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-date\">Jan 01, 1979 00:00:00</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:empty-set</span></th><td class=\"jh-value jh-object-value\"><div class=\"jh-type-set\"><span class=\"jh-empty-set\"></span></div></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:list</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">1</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">2</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">3</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:map</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:foo</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">bar</span></td></tr></tbody></table></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:nil</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-empty\"></span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:object</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">[1, 2]</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:set</span></th><td class=\"jh-value jh-object-value\"><ul><li class=\"jh-value\"><span class=\"jh-type-number\">1</span></li><li class=\"jh-value\"><span class=\"jh-type-number\">2</span></li><li class=\"jh-value\"><span class=\"jh-type-number\">3</span></li></ul></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">:vec</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">a</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">b</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-string\">c</span></td></tr></tbody></table></td></tr></tbody></table></div>")

(def edn-input
  {:date (.parse (java.text.SimpleDateFormat. "yyyy") "1979")
   :map {:foo "bar"}
   :list '(1 2 3)
   :vec [\a \b \c]
   :set #{1 2 3}
   :empty-set #{}
   :nil nil
   :object (doto (java.util.ArrayList.) (.add 1) (.add 2))})

(deftest a-test
  (is (= expected-output-from-json (json->html json-input)))
  (is (= expected-output-from-edn (edn->html edn-input))))
