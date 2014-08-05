package org.kew.rmf.transformers.authors;

import org.kew.rmf.transformers.Transformer;

/**
 * Cleans ex (StripExAuthor) after in (StripInAuthor) after removing the
 * basionym (StripBasionymAuthor).
 */
public class CleanedPubAuthors implements Transformer {

    @Override
    public String transform (String s) {
        s = new StripBasionymAuthorTransformer().transform(s);
        s = new StripExAuthorTransformer().transform(s);
        return new StripInAuthorTransformer().transform(s);
    }

}
