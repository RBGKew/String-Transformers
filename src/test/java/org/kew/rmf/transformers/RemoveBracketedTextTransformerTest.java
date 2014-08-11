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

public class RemoveBracketedTextTransformerTest {

	RemoveBracketedTextTransformer transformer = new RemoveBracketedTextTransformer();

	@Test
	public void blank2blank() {
		assertEquals("", transformer.transform(""));
	}

	@Test
	public void leaveOtherStuff() {
		assertEquals("iuoiy*&^%$--tfghvbhjk", transformer.transform("iuoiy*&^%$--tfghvbhjk"));
	}

	@Test
	public void removeRoundBracketsWithText() {
		assertEquals("text", transformer.transform("text (test)"));
	}
	@Test
	public void removeRoundBracketsWithSpecificTextInRoundBrackets() {
		assertEquals("Prodr.", transformer.transform("Prodr. (DC.)"));
	}
	@Test
	public void removeRoundBracketsWithSpecificTextInSquaredBrackets() {
		assertEquals("Bot. Voy. Herald", transformer.transform("Bot. Voy. Herald [Seemann]"));
	}

	@Test
	public void removeRoundBracketsWithoutText() {
		assertEquals("text", transformer.transform("te()xt"));
	}

	@Test
	public void removeSquareBracketsWithText() {
		assertEquals("text", transformer.transform("[test] text"));
	}

	@Test
	public void removeSquareBracketsWithoutText() {
		assertEquals("text", transformer.transform("text[]"));
	}

	@Test
	public void removeCurlyBracketsWithText() {
		assertEquals("text", transformer.transform("{test} text"));
	}

	@Test
	public void removeCurlyBracketsWithoutText() {
		assertEquals("text", transformer.transform("text{}"));
	}

	@Test
	public void severalBrackets() {
		assertEquals("1 3", transformer.transform("1 (2) 3 (4)"));
	}

	@Test
	public void otherCases() {
		assertEquals("Prodr.", transformer.transform("Prodr. (DC.)"));
		assertEquals("Bot. Voy. Herald", transformer.transform("Bot. Voy. Herald [Seemann]"));
	}

	@Test
	public void nestedSameBrackets() {
		assertEquals("One five.", transformer.transform("One (two (three) four) five."));
	}

	@Test
	public void nestedAllBrackets() {
		assertEquals("Lots brackets", transformer.transform("Lots (of round [and square {or curly} (sometimes)]) brackets"));
	}

	@Test
	public void badlyNestedBrackets() {
		assertEquals("Lots are brackets", transformer.transform("Lots (of round [and sq}u)are {or cu()rly} (so{m(eti}mes)]) brackets"));
		assertEquals("Lots are ] brackets", transformer.transform("Lots (of round [and sq}u)are {or cu()rly} (so{m(eti}m)es)] brackets"));
	}
}
