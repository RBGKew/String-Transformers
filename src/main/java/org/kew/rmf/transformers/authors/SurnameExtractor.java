/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers.authors;

import java.util.regex.Pattern;

import org.kew.rmf.transformers.Transformer;

/**
 * This transformer tries to identify all surnames of plant name authors in a string
 * and deletes everything else.
 */
/*
 * For examples see {@link org.kew.rmf.transformers.authors.SurnameExtractorTest}
 */
public class SurnameExtractor implements Transformer {

	private Pattern linnaeusRemoveDot = Pattern.compile("L\\.(?=\\s)");
	private Pattern surnameExtractorPattern = Pattern.compile("(?<!\\p{L})(-*\\p{L}\\.\\s*)(?=[^$\\)])");
	private Pattern linnaeusAddDot = Pattern.compile("L ");

	@Override
	public String transform(String s) {
		// Linnaeus special: first we remove the Dot after "L" in order to make it appear as a surname,
		//  but only where it's very likely to be Linnaeus; afterwards we'll add it again.
		s = linnaeusRemoveDot.matcher(s).replaceAll("L ");

		s = surnameExtractorPattern.matcher(s).replaceAll("");

		s = linnaeusAddDot.matcher(s).replaceAll("L.");
		return s;
	}
}
