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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kew.rmf.utils.Dictionary;

/**
 * Uses a {@link org.kew.rmf.utils.Dictionary} object of which it iterates over
 * the keys to use each as a regular expression; if the pattern matches, it
 * transforms the string accordingly returning the corresponding value of the
 * Dictionary.
 * <br/>
 * If {@link #multiTransform} is set it goes through the whole list of keys in the
 * same way, otherwise it returns after the first match.
 */
public class DictionaryRegexTransformer implements Transformer {

	private Dictionary dictionary;
	private boolean multiTransform = false;

	private List<Pattern> patterns = new ArrayList<>();
	private List<String> replacements = new ArrayList<>();

	@Override
	public String transform(String s) throws TransformationException {
		for (int i = 0; i < patterns.size(); i++) {
			Pattern p = patterns.get(i);
			String r = replacements.get(i);

			Matcher m = p.matcher(s);
			if (m.find()) {
				s = m.replaceAll(r);
				if (this.multiTransform == false) return s;
			}
		}
		return s;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;

		for (Map.Entry<String, String> entry : this.dictionary.entrySet()) {
			Pattern p = Pattern.compile(entry.getKey());
			patterns.add(p);
			replacements.add(entry.getValue());
		}
	}

	public boolean isMultiTransform() {
		return multiTransform;
	}
	public void setMultiTransform(boolean multiTransform) {
		this.multiTransform = multiTransform;
	}
}
