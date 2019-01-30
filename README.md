# geonames-clj
  [![Build Status](https://travis-ci.org/r0man/geonames-clj.png)](https://travis-ci.org/r0man/geonames-clj)
  [![Dependencies Status](http://jarkeeper.com/r0man/geonames-clj/status.png)](http://jarkeeper.com/r0man/geonames-clj)
  [![Gittip](http://img.shields.io/gittip/r0man.svg)](https://www.gittip.com/r0man)

A Clojure library for some of the Geo Names web services.

## Installation

Via Clojars: http://clojars.org/geonames

[![Current Version](https://clojars.org/geonames/latest-version.svg)](https://clojars.org/geonames)

## Usage

### Countries

``` clj
(require '[geonames.countries :as countries])

(first (countries/countries))
;=> {:area 468,
;=>  :capital "Andorra la Vella",
;=>  :continent {:iso-3166-1-alpha-2 "eu"},
;=>  :currency-code "EUR",
;=>  :currency-name "Euro",
;=>  :fips-code "AN",
;=>  :geonames-id 3041565,
;=>  :iso-3166-1-alpha-2 "ad",
;=>  :iso-3166-1-alpha-3 "and",
;=>  :iso-3166-1-numeric 20,
;=>  :languages ("ca"),
;=>  :name "Andorra",
;=>  :neighbours ("es" "fr"),
;=>  :phone-prefix "376",
;=>  :population 84000,
;=>  :post-code-format "AD###",
;=>  :post-code-regexp "^(?:AD)*(\\d{3})$",
;=>  :top-level-domain ".ad"}
```

## License

Copyright (C) 2013-2019 r0man

Distributed under the Eclipse Public License, the same as Clojure.
