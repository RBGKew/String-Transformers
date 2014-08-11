/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts the input to lower case.
 */
public class TitleCaseTransformer extends LowerCaseTransformer {

	Locale locale = Locale.ENGLISH;

	Pattern capitalise = Pattern.compile("\\b(\\p{L}\\p{Mn}*)");

	/**
	 * Converts the input to lower case using the defined locale, default is English.
	 * Then capitalises letters following a word boundary.
	 */
	@Override
	public String transform(String s) {
		StringBuilder sb = new StringBuilder(super.transform(s));

		System.out.println("---");
		Matcher m = capitalise.matcher(s);
		while (m.find()) {
			System.out.println("Found "+m.group(1));
			System.out.println("Length "+m.group(1).length());
			System.out.println("Start "+m.start());
			System.out.println("End "+m.end());
			String match = m.group(1);
			System.out.println("Replacing «"+sb.substring(m.start(), m.end())+"» with «"+match.toUpperCase(getLocale())+"»");
			sb.delete(m.start(), m.end());
			sb.insert(m.start(), match.toUpperCase(getLocale()));
		}

		return sb.toString();
	}
}
