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

import org.junit.Test;
import org.kew.rmf.transformers.YearRangeExtractorTransformer;

public class YearRangeExtractorTransformerTest {

	@Test
	public void testSimple() {
		YearRangeExtractorTransformer transformer = new YearRangeExtractorTransformer();

		assertEquals("1986", transformer.transform("1986"));
		assertEquals("", transformer.transform(" XX YY "));
		assertEquals("1986", transformer.transform(" XX 1986 YY "));
		assertEquals("1986 1996 2006", transformer.transform(" A 1986 B 1996 C 2006 D "));
		assertEquals("1986 1996 2006", transformer.transform(" A 1986. B 1996, C 2006; D "));
		assertEquals("1986-1996 2006", transformer.transform(" A 1986-1996, C 2006; D "));
		assertEquals("1986-1996-2006", transformer.transform(" A 1986-1996-2006; D "));

		assertEquals("2006", transformer.transform(" A 986 B 2996 C 2006 D "));
		assertEquals("2006", transformer.transform(" A 986. B 2996, C 2006; D "));
		assertEquals("2006", transformer.transform(" A 11986 B 2996 C 2006 D "));
		assertEquals("2006", transformer.transform(" A 19861. B 2996, C 2006; D "));

		assertEquals("1986+", transformer.transform(" XX 1986+ YY "));
		assertEquals("1986-1996 2006+", transformer.transform(" A 1986-1996, C 2006+; D "));
	}
}
