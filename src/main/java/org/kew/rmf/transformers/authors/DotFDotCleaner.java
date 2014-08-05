package org.kew.rmf.transformers.authors;

import org.kew.rmf.transformers.RegexDefCollection;
import org.kew.rmf.transformers.Transformer;


/**
 * Removes " f.", ".f." after any alphanumeric Characters (incl. diacritics).
 */
public class DotFDotCleaner extends RegexDefCollection implements Transformer {

    @Override
    public String transform(String s) {
        String c = String.format("(?<=%s)[\\.\\s]f\\.", ALPHANUMDIAC);
        return s.replaceAll(c, "");
    }

}
