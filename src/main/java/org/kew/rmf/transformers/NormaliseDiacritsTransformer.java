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

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * Converts a string to its normalised form using unicode's NFD form
 * (http://unicode.org/reports/tr15/) adding an ASCII equivalent after any
 * identified diacritical character) and then removes
 * all non-ASCII characters from the string
 *
 * In addition to the characters defined by the unicode consortium the following
 * characters are replaced: [[ADDITIONAL)_REPLACEMENTS]]
 */
public class NormaliseDiacritsTransformer implements Transformer {

    @SuppressWarnings(value = { "serial" })
    static Map<String,String> ADDITIONAL_REPLACEMENTS = new HashMap<String,String>() {{
        put("Ø", "O");
        put("ø", "o");
        put("Ł", "L");
        put("ł", "l");
        put("—", "-"); // emdash to hyphen
        put("–", "-"); // ndash to hyphen
    }};

    @Override
    public String transform(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        for (String key:ADDITIONAL_REPLACEMENTS.keySet()) {
            s = s.replaceAll(key, ADDITIONAL_REPLACEMENTS.get(key));
        }
        return s.replaceAll("[^\\p{ASCII}]", "");
    }

}
