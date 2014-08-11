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

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;

public class TitleCaseTransformerTest {

	@Test
	public void testSimple() {
		TitleCaseTransformer transformer = new TitleCaseTransformer();
		assertEquals("To-Morrow Is The 8th August.", transformer.transform("To-morrow is the 8th AUGUST."));
		assertEquals("Mein Geburtstag Ist Der 13. März.", transformer.transform("MeiN Geburtstag ist der 13. MÄRZ."));
		assertEquals("София Е Столица На България.", transformer.transform("СофиЯ е столица на БЪЛГАРИЯ."));
		assertEquals("北京是中国的", transformer.transform("北京是中国的"));
	}

	/**
	 * I'm not expert enough in a language with these characters to know what the result should be.
	 * Should the Lithuanian i-with-dot-with-grave turn back into I-grave?
	 *
	 * Also, {@link java.lang.Character#toTitleCase(int) mentions the case of letters like "Ǆ", "ǆ" and "ǅ".
	 */
	@Ignore
	@Test
	public void testWithLocale() {
		TitleCaseTransformer transformer = new TitleCaseTransformer();

		assertEquals("Aì Ìa", transformer.transform("aÌ ìa")); // English locale, uppercase i with grave accent

		transformer.setLocale(new Locale("lt"));
		assertEquals("Ai̇̀", transformer.transform("aÌ"));
		assertEquals("Ìa", transformer.transform("i̇̀A"));
	}
}
