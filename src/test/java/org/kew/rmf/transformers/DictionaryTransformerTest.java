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

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;
import org.kew.rmf.transformers.DictionaryTransformer;
import org.kew.rmf.transformers.TransformationException;
import org.kew.rmf.utils.Dictionary;

public class DictionaryTransformerTest {

	public class TestDictionary extends HashMap<String,String> implements Dictionary {
		private static final long serialVersionUID = 1L;

		public TestDictionary() {
			put("a", "b");
			put("c", "d");
		}

		@Override
		public String get(String key) {
			return super.get(key);
		}
	}

	@Test
	public void test() throws IOException, TransformationException {
		Dictionary dict = new TestDictionary();

		DictionaryTransformer transformer = new DictionaryTransformer();
		transformer.setDictionary(dict);

		assertEquals("b", transformer.transform("a"));
		assertEquals("b", transformer.transform("b"));
		assertEquals("d", transformer.transform("c"));
		assertEquals("d", transformer.transform("d"));
		assertEquals("e", transformer.transform("e"));
	}
}
