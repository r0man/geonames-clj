# Clojure API for GeoNames.

This is a Clojure API for some [GeoNames](http://www.geonames.org)
services. Currently there is only a country and region parser
implemented.

## Installation

The JAR is available on [Clojars](http://clojars.org/geonames), or use
[Leiningen](http://github.com/technomancy/leiningen) to build your own
...

### Country Parser

The `parse-countries` function returns a country structure for all
countries in the
[countryInfo.txt](http://download.geonames.org/export/dump/countryInfo.txt)
file on the GeoNames [download
server](http://download.geonames.org/export/dump/).

Example:
<pre>
<code>
user> (use 'geonames.countries)
nil
user> (first (parse-countries))
{:area 468, :capital "Andorra la Vella", :continent-code "EU",
:currency-code "EUR", :currency-name "Euro", :fips-code "AN",
:geonames-id 3041565, :iso-3166-alpha-2 "AD", :iso-3166-alpha-3 "AND",
:iso-3166-numeric 20, :languages ("ca" "fr-AD" "pt"), :name "Andorra",
:neighbours ("ES" "FR"), :phone-prefix "376", :population 72000,
:post-code-format "AD###", :post-code-regexp "^(?:AD)*(\\d{3})$",
:top-level-domain ".ad"}
</code>
</pre>

### Region Parser

The `parse-regions` function returns a region structure for all
regions found in the
[admin1Codes.txt](http://download.geonames.org/export/dump/admin1Codes.txt)
file on the GeoNames [download
server](http://download.geonames.org/export/dump/).

Example:
<pre>
<code>
user> (use 'geonames.regions)
nil
user> (first (parse-regions))
{:name "Andorra (general)", :country-id "AD", :region-id "00", :geonames-id "AD.00"}
</code>
</pre>

### License

Copyright (C) 2010 Roman Scherer.

Distributed under the Eclipse Public License, the same as Clojure
uses. See the file COPYING for more information.
