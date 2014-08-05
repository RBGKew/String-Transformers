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
