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

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Removes diacritical marks from letters, intended for dealing with OCR errors
 * and language-ignorant string comparisons.
 * <br/>
 * Converts a string to its normalised form using unicode's NFKD form
 * (http://unicode.org/reports/tr15/).  Translates some non-English letters
 * to what an English speaker might consider an unaccented letter.
 */
public class NormaliseDiacriticalMarksTransformer implements Transformer {

	/*
	 * See http://www.regular-expressions.info/unicode.html for Unicode character classes.
	 * See also Lucene's ASCIIFoldingFilter, which deals with a far greater range of characters.
	 */

	private final Pattern accents = Pattern.compile("[\\p{M}]");

	private final Map<String,String> additionalReplacements;
	public NormaliseDiacriticalMarksTransformer() {
		additionalReplacements = new HashMap<>();
		additionalReplacements.put("Æ", "AE");
		additionalReplacements.put("æ", "ae");
		additionalReplacements.put("Œ", "OE");
		additionalReplacements.put("œ", "oe");
		additionalReplacements.put("Ø", "O");
		additionalReplacements.put("ø", "o");
		additionalReplacements.put("Ł", "L");
		additionalReplacements.put("ł", "l");
		additionalReplacements.put("Đ", "D");
		additionalReplacements.put("đ", "d");
		additionalReplacements.put("—", "-"); // emdash to hyphen
		additionalReplacements.put("–", "-"); // ndash to hyphen
	};

	@Override
	public String transform(String s) {
		s = Normalizer.normalize(s, Normalizer.Form.NFKD);
		for (String key : additionalReplacements.keySet()) {
			s = s.replace(key, additionalReplacements.get(key));
		}
		return accents.matcher(s).replaceAll("");
	}
}
