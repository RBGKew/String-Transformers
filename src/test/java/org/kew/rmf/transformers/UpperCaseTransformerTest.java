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

import org.junit.Test;

public class UpperCaseTransformerTest {

	@Test
	public void testSimple() {
		UpperCaseTransformer transformer = new UpperCaseTransformer();
		assertEquals("TODAY IS THE 8TH AUGUST.", transformer.transform("Today is the 8th August."));
		assertEquals("MEIN GEBURTSTAG IST DER 13. MÄRZ.", transformer.transform("Mein Geburtstag ist der 13. März."));
		assertEquals("СОФИЯ Е СТОЛИЦА НА БЪЛГАРИЯ.", transformer.transform("София е столица на България."));
		assertEquals("北京是中国的", transformer.transform("北京是中国的"));
	}

	@Test
	public void testWithLocale() {
		UpperCaseTransformer transformer = new UpperCaseTransformer();

		assertEquals("Ì", transformer.transform("ì")); // English locale, uppercase i with grave accent

		transformer.setLocale(Locale.GERMAN);
		assertEquals("SS", transformer.transform("ß")); // Eszet to SS.
	}
}
