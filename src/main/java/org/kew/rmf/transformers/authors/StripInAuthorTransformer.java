/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers.authors;

import java.util.Locale;
import java.util.regex.Pattern;

import org.kew.rmf.transformers.Transformer;

/**
 * This transformer translates author strings in the form "Author1 in Author2" to "Author1"
 */
public class StripInAuthorTransformer implements Transformer{

	// TODO: Check this is general enough, e.g. word boundaries instead?
	protected static final String IN_MARKER = " in ";
	protected static final String IN_MARKER_REGEX =  "( [Ii][Nn] )";

	private Pattern inPattern = Pattern.compile(IN_MARKER_REGEX + ".*$");

	@Override
	public String transform(String s) {
		if (s == null) return null;

		if (s.toLowerCase(Locale.ENGLISH).indexOf(IN_MARKER) != -1) {
			s = inPattern.matcher(s).replaceAll("");
		}
		return s;
	}
}
