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

import java.util.regex.Pattern;

/**
 * This is a chain of three transformations:
 * <ol>
 * 	<li>replace diacritical characters with their Latin equivalent ({@link NormaliseDiacriticalMarksTransformer})</li>
 * 	<li>replace all non-alphanumeric characters with {@link #replacement} (default: space)</li>
 * 	<li>replace multiple whitespace occurrences with one whitespace</li>
 * </ul>
 * Returns a trimmed result.
 * <br/>
 * This transformer works with Unicode text, unlike {@link StripNonAsciiAlphanumericCharactersTransformer}.
 */
public class StripNonAlphanumericCharactersTransformer implements Transformer {

	private NormaliseDiacriticalMarksTransformer normaliseDiacriticalMarksTransformer = new NormaliseDiacriticalMarksTransformer();
	private SqueezeWhitespaceTransformer squeezeWhitespaceTransformer = new SqueezeWhitespaceTransformer();

	private final Pattern nonAlphanumeric = Pattern.compile("[^\\p{L}\\p{N}]");
	private String replacement = " ";

	@Override
	public String transform(String s) {
		s = normaliseDiacriticalMarksTransformer.transform(s);
		s = nonAlphanumeric.matcher(s).replaceAll(replacement);
		s = squeezeWhitespaceTransformer.transform(s);
		return s.trim();
	}

	public String getReplacement() {
		return replacement;
	}
	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}
}
