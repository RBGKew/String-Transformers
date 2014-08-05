package org.kew.rmf.transformers.authors;

import org.kew.rmf.transformers.RegexDefCollection;
import org.kew.rmf.transformers.Transformer;

/**
 * This transformer translates author strings in the form "Author1 ex Author2" to "Author2"
 */
public class StripExAuthorTransformer extends RegexDefCollection implements Transformer{

    @Override
    public String transform(String s) {
        String cleaned = s;
        if (s != null){
            if (s.toLowerCase().indexOf(EX_MARKER) != -1){
                cleaned = s.replaceAll(".*" + EX_MARKER_REGEX, "");
            }
        }
        return cleaned;
    }

}
