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

import org.kew.rmf.transformers.Transformer;

/**
 * This transformer tries to identify all publication authors of plant names in
 * a string and returns a string where each of their surnames are shrunk/cropped
 * to a length of {@link #setShrinkTo(int)}.
 */
/*
 * For examples of standard usage and corner cases see {@link ShrunkPubAuthorsTest}
 */
public class ShrunkPubAuthors implements Transformer {

	private ShrunkAuthors shrunkAuthors = new ShrunkAuthors();
	private CleanedPubAuthors cleanedPubAuthors = new CleanedPubAuthors();

	@Override
	public String transform(String s) {
		s = cleanedPubAuthors.transform(s);
		s = shrunkAuthors.transform(s);
		return s;
	}

	public Integer getShrinkTo() {
		return shrunkAuthors.getShrinkTo();
	}
	public void setShrinkTo(int shrinkTo) {
		shrunkAuthors.setShrinkTo(shrinkTo);
	}
}
