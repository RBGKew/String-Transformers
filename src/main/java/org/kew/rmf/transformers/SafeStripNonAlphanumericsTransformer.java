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

/**
 * This is just a best-practise chain of three transformations:
 * (1) replaces diacritic characters with their ASCII equivalent (NormaliseDiacriticsTransformer)
 * (2) replaces all non-alphanumeric characters with `b` (default: whitespace)
 * (3) replaces multiple whitespace occurrences with one whitespace
 * returns a trimmed result
 */
public class SafeStripNonAlphanumericsTransformer implements Transformer {

	final private String a = "[^A-Za-z0-9]";
	private String replaceWith = " ";

	@Override
	public String transform(String s) {
		s = new NormaliseDiacritsTransformer().transform(s);
		return s.replaceAll(a, replaceWith).replaceAll("\\s+", " ").trim();
	}

	public String getA() {
		return a;
	}
	public String getReplaceWith() {
		return replaceWith;
	}
	public void setReplaceWith(String replaceWith) {
		this.replaceWith = replaceWith;
	}

}
