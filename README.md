## json-html

Generates a DOM node with a human representation of the JSON/EDN encoded data. Based on the [json.human.js](http://marianoguerra.github.io/json.human.js/) library.

## Installation

#### Leiningen

```clojure
[json-html "0.1.0"]
```

## Usage

```clojure
(use 'json-html.core)

(edn->html {:foo [1 2 3] :bar "baz"})
```
<div class=\"jh-root\"><table class=\"jh-type-object\"><tr><th class=\"jh-key jh-object-key\">foo</th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">1</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">2</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">3</span></td></tr></table></td></tr><tr><th class=\"jh-key jh-object-key\">bar</th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">baz</span></td></tr></table></div>

```clojure
(json->html "{\"foo\": [1, 2, 3], \"bar\": \"baz\"}")
```
<div class=\"jh-root\"><table class=\"jh-type-object\"><tr><th class=\"jh-key jh-object-key\">foo</th><td class=\"jh-value jh-object-value\"><table class=\"jh-type-object\"><tr><th class=\"jh-key jh-array-key\">0</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">1</span></td></tr><tr><th class=\"jh-key jh-array-key\">1</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">2</span></td></tr><tr><th class=\"jh-key jh-array-key\">2</th><td class=\"jh-value jh-array-value\"><span class=\"jh-type-number\">3</span></td></tr></table></td></tr><tr><th class=\"jh-key jh-object-key\">bar</th><td class=\"jh-value jh-object-value\"><span class=\"jh-type-string\">baz</span></td></tr></table></div>

A default CSS is provided in `resources/json.human.css`.

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

<div class="jh-root"><table class="jh-type-object"><tr><th class="jh-key jh-object-key">license</th><td class="jh-value jh-object-value"><span class="jh-type-string">MIT</span></td></tr><tr><th class="jh-key jh-object-key">config</th><td class="jh-value jh-object-value"><table class="jh-type-object"><tr><th class="jh-key jh-object-key"> some funky KEY</th><td class="jh-value jh-object-value"><span class="jh-type-string">funky</span></td></tr><tr><th class="jh-key jh-object-key">bool</th><td class="jh-value jh-object-value"><span class="jh-type-bool">true</span></td></tr><tr><th class="jh-key jh-object-key">htmlEntities</th><td class="jh-value jh-object-value"><span class="jh-type-string">   &lt;- trailing &lt;em&gt;   &amp; &lt;/em&gt; and some html   </span></td></tr><tr><th class="jh-key jh-object-key">emptyArray</th><td class="jh-value jh-object-value"><table class="jh-type-object"></table></td></tr><tr><th class="jh-key jh-object-key">customization?</th><td class="jh-value jh-object-value"><table class="jh-type-object"><tr><th class="jh-key jh-array-key">0</th><td class="jh-value jh-array-value"><span class="jh-type-string">customize the css prefix</span></td></tr><tr><th class="jh-key jh-array-key">1</th><td class="jh-value jh-array-value"><span class="jh-type-string">change the css file</span></td></tr></table></td></tr><tr><th class="jh-key jh-object-key">emptyObject</th><td class="jh-value jh-object-value"><table class="jh-type-object"></table></td></tr><tr><th class="jh-key jh-object-key">how?</th><td class="jh-value jh-object-value"><table class="jh-type-object"><tr><th class="jh-key jh-array-key">0</th><td class="jh-value jh-array-value"><span class="jh-type-string">add json.human.js</span></td></tr><tr><th class="jh-key jh-array-key">1</th><td class="jh-value jh-array-value"><span class="jh-type-string">add json.human.css</span></td></tr><tr><th class="jh-key jh-array-key">2</th><td class="jh-value jh-array-value"><span class="jh-type-string">???</span></td></tr><tr><th class="jh-key jh-array-key">3</th><td class="jh-value jh-array-value"><span class="jh-type-string">profit!</span></td></tr></table></td></tr><tr><th class="jh-key jh-object-key">what?</th><td class="jh-value jh-object-value"><span class="jh-type-string">this object is just to show some extra stuff</span></td></tr><tr><th class="jh-key jh-object-key">float</th><td class="jh-value jh-object-value"><span class="jh-type-number">12.3</span></td></tr><tr><th class="jh-key jh-object-key">integer</th><td class="jh-value jh-object-value"><span class="jh-type-number">42</span></td></tr><tr><th class="jh-key jh-object-key">emptyString</th><td class="jh-value jh-object-value"><span class="jh-type-string"></span></td></tr></table></td></tr><tr><th class="jh-key jh-object-key">dependencies</th><td class="jh-value jh-object-value"><table class="jh-type-object"><tr><th class="jh-key jh-object-key">crel</th><td class="jh-value jh-object-value"><span class="jh-type-string">1.0.0</span></td></tr></table></td></tr><tr><th class="jh-key jh-object-key">repository</th><td class="jh-value jh-object-value"><table class="jh-type-object"><tr><th class="jh-key jh-object-key">type</th><td class="jh-value jh-object-value"><span class="jh-type-string">git</span></td></tr><tr><th class="jh-key jh-object-key">url</th><td class="jh-value jh-object-value"><span class="jh-type-string">git://github.com/marianoguerra/json.human.js.git</span></td></tr></table></td></tr><tr><th class="jh-key jh-object-key">author</th><td class="jh-value jh-object-value"><span class="jh-type-string">Mariano Guerra &lt;mariano@marianoguerra.org&gt;</span></td></tr><tr><th class="jh-key jh-object-key">name</th><td class="jh-value jh-object-value"><span class="jh-type-string">json.human</span></td></tr><tr><th class="jh-key jh-object-key">version</th><td class="jh-value jh-object-value"><span class="jh-type-string">0.1.0</span></td></tr><tr><th class="jh-key jh-object-key">contributors</th><td class="jh-value jh-object-value"><table class="jh-type-object"></table></td></tr><tr><th class="jh-key jh-object-key">tags</th><td class="jh-value jh-object-value"><table class="jh-type-object"><tr><th class="jh-key jh-array-key">0</th><td class="jh-value jh-array-value"><span class="jh-type-string">DOM</span></td></tr><tr><th class="jh-key jh-array-key">1</th><td class="jh-value jh-array-value"><span class="jh-type-string">HTML</span></td></tr><tr><th class="jh-key jh-array-key">2</th><td class="jh-value jh-array-value"><span class="jh-type-string">JSON</span></td></tr><tr><th class="jh-key jh-array-key">3</th><td class="jh-value jh-array-value"><span class="jh-type-string">Pretty Print</span></td></tr></table></td></tr><tr><th class="jh-key jh-object-key">main</th><td class="jh-value jh-object-value"><span class="jh-type-string">json.human.js</span></td></tr><tr><th class="jh-key jh-object-key">description</th><td class="jh-value jh-object-value"><span class="jh-type-string">Convert
 JSON to human readable
 HTML</span></td></tr><tr><th class="jh-key jh-object-key">bugs</th><td class="jh-value jh-object-value"><table class="jh-type-object"><tr><th class="jh-key jh-object-key">url</th><td class="jh-value jh-object-value"><span class="jh-type-string">https://github.com/yogthos/json-html/issues</span></td></tr></table></td></tr></table></div>
 
