[![Build Status](https://travis-ci.org/RBGKew/String-Transformers.svg?branch=master)](https://travis-ci.org/RBGKew/String-Transformers)

# String Transformers

This is a collection of string transformers, all of which implement a `transform` method that takes a string and returns another.

Some are generic: `CapitalLettersExtractor` removes non-capital letters from a string.  Others are geared towards handling scientific names, like `StripBasionymAuthorTransformer`.

**See the [String-Transformers Wiki on GitHub](https://github.com/RBGKew/String-Transformers/wiki) or [API Documentation](https://rbgkew.github.io/String-Transformers/apidocs/0.1.0/) for further documentation.**

## Usage with OpenRefine

The transformers can be used with [OpenRefine](http://www.openrefine.org/).  [Download the JAR](http://repo1.maven.org/maven2/org/kew/rmf/string-transformers/0.1.0/string-transformers-0.1.0.jar)
and [Apache Commons-Lang3 JAR](http://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar) and
put both in OpenRefine's `webapp/extensions/jython/module/MOD-INF/lib` directory, then (re)start OpenRefine.

Use expressions like this, after selecting _Jython_ from the Language dropdown:

```python
from org.kew.rmf.transformers import CapitalLettersExtractor

cle = CapitalLettersExtractor()
return cle.transform(value)
```

## Usage in other software

The library is published in the Maven Central Repository:

```xml
<dependency>
	<groupId>org.kew.rmf</groupId>
	<artifactId>string-transformers</artifactId>
	<version>0.1.0</version>
</dependency>
```

## Licensing, contributions and credits

String-Transformers is open source software licensed under the MIT license, see `LICENSE.md`.  The software was created at the [Royal Botanic Gardens, Kew](http://www.kew.org/).  For details of
developers see the Git commit history.  Thanks also to the Data Improvement Team for their scientific input.

Further contributions are welcome!  We’re also interested to know if you use this library, whether that’s for biodiversity data or something else.  Contact [Matthew Blissett](mailto:M.Blissett@kew.org?subject=String-Transformers) or
use [GitHub](https://github.com/RBGKew/String-Transformers).
