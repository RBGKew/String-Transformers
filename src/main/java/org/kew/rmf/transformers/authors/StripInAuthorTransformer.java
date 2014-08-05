package org.kew.rmf.transformers.authors;

import org.kew.rmf.transformers.RegexDefCollection;
import org.kew.rmf.transformers.Transformer;

/**
 * This transformer translates author strings in the form "Author1 in Author2" to "Author1"
 */
public class StripInAuthorTransformer extends RegexDefCollection implements Transformer{

    @Override
    public String transform(String s) {
        String cleaned = s;
        if (s != null){
            if (s.toLowerCase().indexOf(IN_MARKER) != -1){
                cleaned = s.replaceAll(IN_MARKER_REGEX + ".*$", "");
            }
        }
        return cleaned;
    }

}
