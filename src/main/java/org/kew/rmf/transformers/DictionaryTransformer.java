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

import org.kew.rmf.utils.Dictionary;

/**
 * Uses a {@link org.kew.rmf.utils.Dictionary} object to lookup a string in its
 * keys and returns the value if the key is found. Otherwise it returns the
 * original string.
 */
public class DictionaryTransformer implements Transformer {

    Dictionary dictionary;

    @Override
    public String transform(String s) throws TransformationException {
        String value = this.dictionary.get(s);
        return (value != null) ? value : s;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
