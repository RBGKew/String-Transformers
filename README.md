[![Build Status](https://travis-ci.org/RBGKew/String-Transformers.svg?branch=master)](https://travis-ci.org/RBGKew/String-Transformers)

# String Transformers

This is a collection of string transformers, all of which implement a `transform` method that takes a string and returns another.

Some are generic: `CapitalLettersExtractor` removes non-capital letters from a string.  Others are geared towards handling scientific names, like `StripBasionymAuthorTransformer`.

## Usage with OpenRefine

The transformers can be used with [OpenRefine](http://www.openrefine.org/).  [Download (temporary location)](http://bitbucket.matt.blissett.me.uk/string-transformers-1.0.0-SNAPSHOT.jar)
the JAR and put it in OpenRefine's `webapp/extensions/jython/module/MOD-INF/lib` directory, then (re)start OpenRefine.

Use expressions like this, after selecting _Jython_ from the Language dropdown:

```python
from org.kew.rmf.transformers import CapitalLettersExtractor

cle = CapitalLettersExtractor()
return cle.transform(value)
```

## Licensing, contributions and credits

String-Transformers is open source software licensed under the MIT license, see `LICENSE.md`.  The software was created by developers at the [Royal Botanic Gardens, Kew](http://www.kew.org/).  Contributers are
* Nicky Nicolson
* Nick Black
* Matthew Blissett (maintainer; M.Blissett@kew.org)
* Alecs Geuder

Further contributions are welcome!  We’re also interested to know if you use this library, whether that’s for biodiversity data or something else.
