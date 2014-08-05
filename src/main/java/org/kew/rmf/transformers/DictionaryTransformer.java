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
