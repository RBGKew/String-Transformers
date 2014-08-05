package org.kew.rmf.transformers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kew.rmf.transformers.SafeStripNonAlphanumericsTransformer;
import org.kew.rmf.transformers.Transformer;

public class SafeStripNonAlphaNumericsTransformerTest {

	@Test
	public void blank2blank () throws Exception {
		Transformer transformer = new SafeStripNonAlphanumericsTransformer();
		assertEquals("", transformer.transform(""));
	}
	
	@Test
	public void customReplacement() throws Exception {
		SafeStripNonAlphanumericsTransformer transformer = new SafeStripNonAlphanumericsTransformer();
		transformer.setReplaceWith("");
		assertEquals("hyphenatedepithet", transformer.transform("hyphenated-epithet"));
	}

	@Test
	public void wordWithHybridSignToAscii () throws Exception {
		Transformer transformer = new SafeStripNonAlphanumericsTransformer();
		assertEquals("hybrid species", transformer.transform("× hybrid-species"));
	}

	@Test
	public void numericsAresafe () throws Exception {
		Transformer transformer = new SafeStripNonAlphanumericsTransformer();
		assertEquals("text 123 text 4 5", transformer.transform("text 123 text 4.5"));
	}

	@Test
	public void withHyphenAndNumbersAndPunctuation () throws Exception {
		Transformer transformer = new SafeStripNonAlphanumericsTransformer();
		assertEquals("Tete a tete en 2", transformer.transform("Tête-à-tête en 2"));
	}
	
	@Test
	public void replaceEmDashes () throws Exception {
		Transformer transformer = new SafeStripNonAlphanumericsTransformer();
		assertEquals("hello emdash", transformer.transform("hello—emdash"));
	}

	@Test
	public void replaceNDashes () throws Exception {
		Transformer transformer = new SafeStripNonAlphanumericsTransformer();
		assertEquals("hello ndash", transformer.transform("hello–ndash"));
	}

	@Test
	public void testDifferentDiacritics () throws Exception {
		Transformer transformer = new SafeStripNonAlphanumericsTransformer();
		assertEquals("Sorensen", transformer.transform("Sørensen"));
		assertEquals("Muller", transformer.transform("Müller"));
		assertEquals("Moller", transformer.transform("Möller"));
		assertEquals("E Desv", transformer.transform("É.Desv."));
		assertEquals("c or c", transformer.transform("č or ĉ"));
		assertEquals("L and l", transformer.transform("Ł and ł"));
		assertEquals("A", transformer.transform("Ă"));
	}

}
