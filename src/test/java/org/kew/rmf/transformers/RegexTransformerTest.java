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

import static org.junit.Assert.*;

import org.junit.Test;
import org.kew.rmf.transformers.RegexTransformer;

public class RegexTransformerTest {

	@Test
	public void testSimple() {
		RegexTransformer transformer = new RegexTransformer();
		transformer.setPattern("Jelly");
		transformer.setReplacement("Smelly");
		assertEquals("Smelly fish", transformer.transform("Jelly fish"));
	}

	@Test
	public void testRegEx() {
		RegexTransformer transformer = new RegexTransformer();
		transformer.setPattern("[^a-zA-Z\\s]");
		transformer.setReplacement("");
		assertEquals("Tidy String", transformer.transform("Tidy 1251258215123412String..//**#"));
	}

	/**
	 * Removes repeated letters.
	 */
	@Test
	public void testRegExMatchGroups() {
		RegexTransformer transformer = new RegexTransformer();
		transformer.setPattern("(\\p{L})\\1+");
		transformer.setReplacement("$1");

		assertEquals("Aple buble crash!!!", transformer.transform("Apple bubble ccccrrrraaassssssshhhhhhh!!!"));
	}
}
