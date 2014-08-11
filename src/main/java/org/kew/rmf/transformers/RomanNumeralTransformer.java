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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This transformer splits a string into a series of words. The word delimiter is any 
 * sequence of non-alphanumeric characters. It then iterates over the "words" and 
 * converts any Roman numerals to their Arabic equivalent, then concatenates these 
 * converted words back into a single string, using the space character to separate words.
 */
public class RomanNumeralTransformer implements Transformer {

	private final Pattern nonAsciiAlphanumeric = Pattern.compile("[^A-Za-z0-9]");
	private final Pattern multipleWhitespace = Pattern.compile("\\s+");

	private final Map<String,String> map = new HashMap<String,String>();
	private final static String[] BASIC_ROMAN_NUMBERS = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	private final static int[] BASIC_VALUES = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	public RomanNumeralTransformer() {
		for (int i = 1; i <= 5000; i++) {
			map.put(toRomanValue(i), Integer.toString(i));
		}
	}

	public static String toRomanValue(int arabicValue) {
		StringBuffer sb = new StringBuffer();
		int remainder = arabicValue;
		for (int i = 0; i < BASIC_VALUES.length; i++) {
			while (remainder >= BASIC_VALUES[i]) {
				sb.append(BASIC_ROMAN_NUMBERS[i]);
				remainder -= BASIC_VALUES[i];
			}
		}
		return sb.toString();
	}

	@Override
	public String transform(String s) {
		String[] words = multipleWhitespace.matcher(nonAsciiAlphanumeric.matcher(s).replaceAll(" ")).replaceAll(" ").split(" ");
		String[] converted_words = new String[words.length];
		for (int i = 0; i < words.length; i++) {
			String roman = map.get(words[i].toUpperCase());
			if (roman != null) {
				converted_words[i] = roman;
			}
			else {
				converted_words[i] = words[i];
			}
		}
		StringBuffer sb = new StringBuffer();
		for (String converted_word : converted_words) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(converted_word);
		}
		return sb.toString();
	}
}
