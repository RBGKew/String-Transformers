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

public class NormaliseDiacriticalMarksTransformerTest {

	NormaliseDiacriticalMarksTransformer transformer = new NormaliseDiacriticalMarksTransformer();

	@Test
	public void test() {
		assertEquals("Marz Lodz O Umea c c d s z office", transformer.transform("März Łodz Ø Umeå č ć đ š ž oﬃce"));
		assertEquals("1-2 - 3", transformer.transform("1–2 — 3"));
	}

	/**
	 * Beware — we might expect to get entirely As from this, but we don't.
	 */
	@Test
	public void testAs() {
		String lotsAs = "À Á Â Ã Ä Å Ā Ă Ą Ə Ǎ Ǟ Ǡ Ǻ Ȁ Ȃ Ȧ Ⱥ ᴀ Ḁ Ạ Ả Ấ Ầ Ẩ Ẫ Ậ Ắ Ằ Ẳ Ẵ Ặ Ⓐ Ａ à á â ã ä å ā ă ą ǎ ǟ ǡ ǻ ȁ ȃ ȧ ɐ ə ɚ ᶏ ᶕ ạ ả ạ ả ấ ầ ẩ ẫ ậ ắ ằ ẳ ẵ ặ ₐ ₔ ⓐ ⱥ Ɐ ａ Ꜳ Æ Ǣ Ǽ ᴁ Ꜵ Ꜷ Ꜹ Ꜻ Ꜽ ⒜ ꜳ æ ǣ ǽ ᴂ ꜵ ꜷ ꜹ ꜻ ꜽ";
		String moreAs = "A A A A A A A A A Ə A A A A A A A Ⱥ ᴀ A A A A A A A A A A A A A A A a a a a a a a a a a a a a a a a ɐ ə ɚ ᶏ ᶕ a a a a a a a a a a a a a a a ə a ⱥ Ɐ a Ꜳ AE AE AE ᴁ Ꜵ Ꜷ Ꜹ Ꜻ Ꜽ (a) ꜳ ae ae ae ᴂ ꜵ ꜷ ꜹ ꜻ ꜽ";

		assertEquals(moreAs, transformer.transform(lotsAs));
	}
}
