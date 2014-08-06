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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts year ranges from arbitrary strings.  Example: "12 Feb 1845, 1880-95, 1899-1920, June 1950+" becomes "1845 1880-95 1899-1920 1950+".
 */
public class YearRangeExtractorTransformer implements Transformer {

	String regex = "\\b(1[6789]\\d\\d|20[012]\\d)([–—-]\\b|\\b\\+|\\b)";

	@Override
	public String transform(String s) {
		StringBuffer sb = new StringBuffer();
		if (s != null) {
			Pattern patt = Pattern.compile(regex);
			Matcher m = patt.matcher(s);
			while (m.find()) {
				String match = m.group();
				match = match.replace('–', '-'); // en-dash
				match = match.replace('—', '-'); // em-dash
				sb.append(match);
				if (!match.endsWith("-")) {
					sb.append(" ");
				}
			}
		}
		return sb.toString().trim();
	}
}
