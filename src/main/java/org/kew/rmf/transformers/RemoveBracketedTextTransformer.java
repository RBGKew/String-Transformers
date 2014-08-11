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

/**
 * Removes all text in brackets (round, square and curly) incl. the brackets.
 */
public class RemoveBracketedTextTransformer implements Transformer {

	private SqueezeWhitespaceTransformer whitespaceSqueezer = new SqueezeWhitespaceTransformer();

	@Override
	public String transform(String s) {
		if (s == null) return null;

		char[] original = s.toCharArray();
		char[] stripped = new char[original.length];

		int pos = 0;

		int count = 0;

		char starter = 0;
		char terminator = 0;

		for (int i = 0; i < original.length; i++) {
			boolean closed = false;

			// If I'm inside some brackets...
			if (count > 0) {

				// Check for opening brackets of the same kind
				if (original[i] == starter) {
					count++;
				}
				// Or closing brackets of the right kind
				else if (original[i] == terminator) {
					// Brackets are only ending if this reduces the count to zero
					count--;

					// But that's only the end if we're out of nested brackets
					if (count == 0) {
						closed = true;
					}
				}
			}
			// If I'm not inside, check for a new sequence and set the terminator.
			else {
				switch (original[i]) {
				case '(':
					count++;
					starter = '(';
					terminator = ')';
					break;
				case '[':
					count++;
					starter = '[';
					terminator = ']';
					break;
				case '{':
					count++;
					starter = '{';
					terminator = '}';
					break;
				default:
				}
			}

			if (count > 0 || closed) {
				// Ignore this character
			}
			else {
				stripped[pos++] = original[i];
			}
		}

		String strippedString = new String(stripped);

		return whitespaceSqueezer.transform(strippedString);
	}
}
