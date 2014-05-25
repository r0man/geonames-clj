# geonames-clj
  [![Build Status](https://travis-ci.org/r0man/geonames-clj.png)](https://travis-ci.org/r0man/geonames-clj)
  [![Dependencies Status](http://jarkeeper.com/r0man/geonames-clj/status.png)](http://jarkeeper.com/r0man/geonames-clj)
  [![Gittip](http://img.shields.io/gittip/r0man.svg)](https://www.gittip.com/r0man)

A Clojure library for some of the Geo Names web services.

## Installation

Via Clojars: http://clojars.org/geonames

[![Current Version](https://clojars.org/geonames-clj/latest-version.svg)](https://clojars.org/geonames-clj)

## Usage

### Countries

``` clj
(use 'geonames.countries)

(first (countries))
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

### Geocoder

``` clj
(use 'geonames.geocoder)

(find-nearby-place-name {:latitude 52.54254 :longitude 13.423033})
;=> [{:lng 13.423426151275635,
;=>   :countryName "Germany",
;=>   :geonameId 3213806,
;=>   :fclName "city, village,...",
;=>   :name "Prenzlauer Berg",
;=>   :toponymName "Prenzlauer Berg",
;=>   :adminCode1 "16",
;=>   :fcode "PPLX",
;=>   :lat 52.55128007275792,
;=>   :population 0,
;=>   :adminName1 "Berlin",
;=>   :countryCode "DE",
;=>   :fcl "P",
;=>   :distance "0.97217",
;=>   :fcodeName "section of populated place"}]
```

## License

Copyright (C) 2013-2014 Roman Scherer

Distributed under the Eclipse Public License, the same as Clojure.
