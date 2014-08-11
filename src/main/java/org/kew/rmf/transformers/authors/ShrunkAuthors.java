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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.kew.rmf.transformers.SqueezeWhitespaceTransformer;
import org.kew.rmf.transformers.StringShrinkerTransformer;
import org.kew.rmf.transformers.StripNonAlphanumericCharactersTransformer;
import org.kew.rmf.transformers.Transformer;

/**
 * This transformer tries to identify <strong>all</strong> authors (accepts publishing-,
 * basionym-, ex-, in-) of plant names in a string and returns a string where
 * each of their surnames are shrunk/cropped to a length
 * of {@link #setShrinkTo(int)}.
 */
/*
 * For examples of standard usage and corner cases see {@link ShrunkAuthorsTest}
 */
public class ShrunkAuthors implements Transformer {

	private DotFDotCleaner dotFDotCleaner = new DotFDotCleaner();
	private SurnameExtractor surnameExtractor = new SurnameExtractor();

	private StripBasionymAuthorTransformer stripBasionymAuthor =new StripBasionymAuthorTransformer();
	private StripPublishingAuthorTransformer stripPublishingAuthor = new StripPublishingAuthorTransformer();

	private Pattern inEx = Pattern.compile(StripExAuthorTransformer.EX_MARKER_REGEX + "|" + StripInAuthorTransformer.IN_MARKER_REGEX);

	private SqueezeWhitespaceTransformer shrinkWhitespace = new SqueezeWhitespaceTransformer();
	private StripNonAlphanumericCharactersTransformer safeStripNonAlphanumerics = new StripNonAlphanumericCharactersTransformer();

	private StringShrinkerTransformer stringShrinker = new StringShrinkerTransformer();

	@Override
	public String transform(String s) {
		if (s == null) return null;

		s = dotFDotCleaner.transform(s);
		s = surnameExtractor.transform(s);

		String pub = stripBasionymAuthor.transform(s);
		String bas = stripPublishingAuthor.transform(s);

		List<String> surnames = new ArrayList<>();
		for (String authors : new String[] {bas, pub}) {
			authors = inEx.matcher(authors).replaceAll(" ");
			authors = shrinkWhitespace.transform(authors);
			authors = safeStripNonAlphanumerics.transform(authors);
			for (String author : authors.split(" ")) {
				// shrink each identified author surname to shrinkTo if set
				author = stringShrinker.transform(author);
				author = author.trim();
				if (author.length() > 0) {
					surnames.add(author);
				}
			}
		}
		return StringUtils.join(surnames, " ").toLowerCase(Locale.ENGLISH);
	}

	public Integer getShrinkTo() {
		return stringShrinker.getLength();
	}
	public void setShrinkTo(int shrinkTo) {
		stringShrinker.setLength(shrinkTo);
	}
}
