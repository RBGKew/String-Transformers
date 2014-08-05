package org.kew.rmf.transformers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.junit.Test;
import org.kew.rmf.transformers.DictionaryRegexTransformer;
import org.kew.rmf.transformers.TransformationException;
import org.kew.rmf.utils.Dictionary;

public class DictionaryRegexTransformerTest {

	public class TestDictionary extends LinkedHashMap<String,String> implements Dictionary {
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

		DictionaryRegexTransformer transformer = new DictionaryRegexTransformer();
		transformer.setDictionary(dict);

		assertEquals("bbcdefg", transformer.transform("abcdefg"));
		transformer.setMultiTransform(true);
		assertEquals("bbddefg", transformer.transform("abcdefg"));
	}
}
