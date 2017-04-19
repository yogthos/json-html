## json-html

Generates a DOM node with a human representation of the JSON/EDN encoded data. Based on the [json.human.js](http://marianoguerra.github.io/json.human.js/) library.

## Installation

The library provides support for Clojure/Script.

#### Leiningen

[![Clojars Project](http://clojars.org/json-html/latest-version.svg)](http://clojars.org/json-html)

## Usage

```clojure
(use 'json-html.core)

(edn->html {:foo [1 2 3] :bar "baz"})
```
<div class=\"jh-root\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">bar</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">baz</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">foo</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">1</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">2</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">3</span></td></tr></tbody></table></td></tr></tbody></table></div>

```clojure
(json->html "{\"foo\": [1, 2, 3], \"bar\": \"baz\"}")
```
<div class=\"jh-root\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">bar</span></th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">baz</span></td></tr><tr><th class=\"jh-key jh-object-key\"><span class=\"jh-type-string\">foo</span></th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tbody><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">1</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">2</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">3</span></td></tr></tbody></table></td></tr></tbody></table></div>

The ClojureScript version has additional functions called `edn->hiccup` and `json->hiccup`, these can be used to generate Hiccup forms for use with other libraries such as [Reagent](http://holmsand.github.io/reagent/).


A default CSS is provided in `resources/json.human.css` and can be included as follows:

```clojure
(ns foo
  (:use json-html.core hiccup.page))

(spit "formatted.html"
     (html5
      [:head [:style (-> "json.human.css" clojure.java.io/resource slurp)]]
      (edn->html [:foo "bar" :baz [1 2 3]])))
```

### Custom data types and render functions

To render data types that are not covered but the library (i.e. `cljs.core/UUID`) or redefine how already
covered data types are rendered, extend the `json-html.core/IRenderHTML` protocol for your type. For example,
to render `UUID`s in the same way you would render its backing string, add this snippet in your code:

```clj
(extend-protocol json-html.core/IRenderHTML
  cljs.core/UUID
  (render-html [u]
    [:span.jh-type-string (str u)]))
```

## Example:

### Input JSON:

```javascript
{
  "name": "json.human",
  "description": "Convert\n JSON to human readable\r HTML",
  "author": "Mariano Guerra <mariano@marianoguerra.org>",
  "tags": ["DOM", "HTML", "JSON", "Pretty Print"],
  "version": "0.1.0",
  "main": "json.human.js",
  "license" : "MIT",
  "dependencies": {
      "crel": "1.0.0"
  },
  "repository": {
    "type": "git",
    "url": "git://github.com/marianoguerra/json.human.js.git"
  },
  "bugs": {
    "url": "https://github.com/yogthos/json-html/issues"
  },
  "contributors": [],
  "config": {
    "what?": "this object is just to show some extra stuff",
    "how?": ["add json.human.js", "add json.human.css", "???", "profit!"],
    "customization?": ["customize the css prefix", "change the css file"],
    "integer": 42,
    "float": 12.3,
    "bool": true,
    "emptyString": "",
    "emptyArray": [],
    "emptyObject": {},
    "htmlEntities": "   <- trailing <em>   & </em> and some html   "
  }
}
```

### Output HTML

<div class="jh-root"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-object-key"><span class="jh-type-string">author</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">Mariano Guerra &lt;mariano@marianoguerra.org&gt;</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">bugs</span></th><td class="jh-value jh-object-value"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-object-key"><span class="jh-type-string">url</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">http://github.com/marianoguerra/json.human.js/issues</span></td></tr></tbody></table></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">config</span></th><td class="jh-value jh-object-value"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-object-key"><span class="jh-type-string">bool</span></th><td class="jh-value jh-object-value"><span class="jh-type-bool">true</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">customization?</span></th><td class="jh-value jh-object-value"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-array-key">0</th><td class="jh-value jh-array-value"><span class="jh-type-string">customize the css prefix</span></td></tr><tr><th class="jh-key jh-array-key">1</th><td class="jh-value jh-array-value"><span class="jh-type-string">change the css file</span></td></tr></tbody></table></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">emptyArray</span></th><td class="jh-value jh-object-value"><div class="jh-type-object"><span class="jh-empty-collection"></span></div></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">emptyObject</span></th><td class="jh-value jh-object-value"><div class="jh-type-object"><span class="jh-empty-map"></span></div></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">emptyString</span></th><td class="jh-value jh-object-value"><span class="jh-type-string"><span class="jh-empty-string"></span></span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">float</span></th><td class="jh-value jh-object-value"><span class="jh-type-number">12.3</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">how?</span></th><td class="jh-value jh-object-value"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-array-key">0</th><td class="jh-value jh-array-value"><span class="jh-type-string">add json.human.js</span></td></tr><tr><th class="jh-key jh-array-key">1</th><td class="jh-value jh-array-value"><span class="jh-type-string">add json.human.css</span></td></tr><tr><th class="jh-key jh-array-key">2</th><td class="jh-value jh-array-value"><span class="jh-type-string">???</span></td></tr><tr><th class="jh-key jh-array-key">3</th><td class="jh-value jh-array-value"><span class="jh-type-string">profit!</span></td></tr></tbody></table></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">htmlEntities</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">   &lt;- trailing &lt;em&gt;   &amp; &lt;/em&gt; and some html   </span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">integer</span></th><td class="jh-value jh-object-value"><span class="jh-type-number">42</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">what?</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">this object is just to show some extra stuff</span></td></tr></tbody></table></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">contributors</span></th><td class="jh-value jh-object-value"><div class="jh-type-object"><span class="jh-empty-collection"></span></div></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">dependencies</span></th><td class="jh-value jh-object-value"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-object-key"><span class="jh-type-string">crel</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">1.0.0</span></td></tr></tbody></table></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">description</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">Convert
 JSON to human readable
 HTML</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">license</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">MIT</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">main</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">json.human.js</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">name</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">json.human</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">repository</span></th><td class="jh-value jh-object-value"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-object-key"><span class="jh-type-string">type</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">git</span></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">url</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">git://github.com/marianoguerra/json.human.js.git</span></td></tr></tbody></table></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">tags</span></th><td class="jh-value jh-object-value"><table class="jh-type-object"><tbody><tr><th class="jh-key jh-array-key">0</th><td class="jh-value jh-array-value"><span class="jh-type-string">DOM</span></td></tr><tr><th class="jh-key jh-array-key">1</th><td class="jh-value jh-array-value"><span class="jh-type-string">HTML</span></td></tr><tr><th class="jh-key jh-array-key">2</th><td class="jh-value jh-array-value"><span class="jh-type-string">JSON</span></td></tr><tr><th class="jh-key jh-array-key">3</th><td class="jh-value jh-array-value"><span class="jh-type-string">Pretty Print</span></td></tr></tbody></table></td></tr><tr><th class="jh-key jh-object-key"><span class="jh-type-string">version</span></th><td class="jh-value jh-object-value"><span class="jh-type-string">0.1.0</span></td></tr></tbody></table></div>

## License

[MIT](http://opensource.org/licenses/MIT)
