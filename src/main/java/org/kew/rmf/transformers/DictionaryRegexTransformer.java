package org.kew.rmf.transformers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kew.rmf.utils.Dictionary;


/**
 * Uses a {@link org.kew.rmf.utils.Dictionary} object of which it iterates over
 * the keys to use each as a regular expression; if the pattern matches, it
 * transforms the string accordingly returning the corresponding value of the
 * Dictionary.
 *
 * If multiTransform is set it goes through the whole list of keys in the
 * same way, otherwise it returns after the first match.
 */
public class DictionaryRegexTransformer implements Transformer {

    private Dictionary dictionary;
    private boolean multiTransform = false;

    @Override
    public String transform(String s) throws TransformationException {
        for (Map.Entry<String, String> entry : this.dictionary.entrySet()) {
            Pattern p = Pattern.compile(entry.getKey());
            Matcher m = p.matcher(s);
            if (m.find()) {
                s = m.replaceAll(entry.getValue());
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
    }

    public boolean isMultiTransform() {
        return multiTransform;
    }
    public void setMultiTransform(boolean multiTransform) {
        this.multiTransform = multiTransform;
    }
}
