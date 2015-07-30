package org.kew.rmf.transformers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CompositeTransformerTest {

	@Test
	public void test() {
		RegexTransformer doubleLetters = new RegexTransformer();
		doubleLetters.setPattern("(\\p{L})\\1+");
		doubleLetters.setReplacement("$1");

		assertEquals("Llanfairpwlgwyngylgogerychwyrndrobwlantysiliogogogoch", doubleLetters.transform("Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch"));

		UpperCaseTransformer upperCase = new UpperCaseTransformer();

		CompositeTransformer composite = new CompositeTransformer();
		composite.addTransformer(upperCase);
		composite.addTransformer(doubleLetters);

		try {
			assertEquals("LANFAIRPWLGWYNGYLGOGERYCHWYRNDROBWLANTYSILIOGOGOGOCH", composite.transform("Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch"));
		}
		catch (TransformationException e) {
			fail();
		}
	}
}
