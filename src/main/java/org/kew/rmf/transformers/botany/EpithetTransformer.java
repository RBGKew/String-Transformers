/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers.botany;

import java.util.regex.Pattern;

import org.kew.rmf.transformers.StripNonAsciiAlphabeticCharactersTransformer;
import org.kew.rmf.transformers.Transformer;

/**
 * This transformer canonicalises epithets.
 */
public class EpithetTransformer implements Transformer {

	private StripNonAsciiAlphabeticCharactersTransformer stripNonAsciiAlphabetic;

	private Pattern ana = Pattern.compile("(?<=[^i])ana$");
	private Pattern aef = Pattern.compile("aef");
	private Pattern colus = Pattern.compile("colus$");
	private Pattern us = Pattern.compile("us$");
	private Pattern um = Pattern.compile("um$");
	private Pattern on = Pattern.compile("on$");
	private Pattern iae = Pattern.compile("iae$");
	private Pattern ei = Pattern.compile("ei$");
	private Pattern ae = Pattern.compile("ae$");
	private Pattern ii = Pattern.compile("ii$");
	private Pattern ioi = Pattern.compile("ioi$");

	public EpithetTransformer() {
		stripNonAsciiAlphabetic = new StripNonAsciiAlphabeticCharactersTransformer();
		stripNonAsciiAlphabetic.setReplacement("");
	}

	@Override
	public String transform(String s) {
		if (s == null) return null;
		s = stripNonAsciiAlphabetic.transform(s);

		s = ana.matcher(s).replaceFirst("iana");
		s = aef.matcher(s).replaceFirst("if");
		s = colus.matcher(s).replaceFirst("cola");
		s = us.matcher(s).replaceFirst("a");
		s = um.matcher(s).replaceFirst("a");
		s = on.matcher(s).replaceFirst("a");
		s = iae.matcher(s).replaceFirst("i");
		s = ei.matcher(s).replaceFirst("i");
		s = ae.matcher(s).replaceFirst("i");
		s = ii.matcher(s).replaceFirst("i");
		s = ioi.matcher(s).replaceFirst("oi");
		s = s.replace("j", "i");
		s = s.replace("y", "i");
		s = s.replace("-", "");
		s = s.replace("'", "");

		/*
		if (value.endsWith("anus") && !value.endsWith("ianus"))
			value = value.replaceFirst("anus$", "ianus");
		if (value.endsWith("anum") && !value.endsWith("ianum"))
			value = value.replaceFirst("anum$", "ianum");
		if (value.endsWith("arum") && !value.endsWith("iarum"))
			value = value.replaceFirst("arum$", "iarum");
		if (value.endsWith("orum") && !value.endsWith("iorum"))
			value = value.replaceFirst("orum$", "iorum");
		*/

		return s;
	}
}
