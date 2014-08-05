package org.kew.rmf.transformers.authors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kew.rmf.transformers.RegexDefCollection;
import org.kew.rmf.transformers.SafeStripNonAlphanumericsTransformer;
import org.kew.rmf.transformers.StringShrinker;
import org.kew.rmf.transformers.Transformer;

/**
 * This transformer tries to identify *all* authors (accepts publishing-,
 * basionym-, ex-, in-) of plant names in a string and returns a string where
 * each of their surnames are shrunk/cropped to a length
 * of `shrinkTo`.
 *
 * For examples of standard usage and corner cases see {@link ShrunkAuthorsTest}
 */
public class ShrunkAuthors extends RegexDefCollection implements Transformer {

    private Integer shrinkTo = null;

    @Override
    public String transform(String s) {
        s = new DotFDotCleaner().transform(s);
        s = new SurnameExtractor().transform(s);
        String pub = new StripBasionymAuthorTransformer().transform(s);
        String bas = new StripPublishingAuthorTransformer().transform(s);
        String exIn = String.format("%s|%s", EX_MARKER_REGEX, IN_MARKER_REGEX);
        List<String> surnames = new ArrayList<>();
        for (String authors: new String[] {bas, pub}) {
            authors = authors.replaceAll(exIn, " ");
            authors = authors.replaceAll("\\s+", " ");
            authors = new SafeStripNonAlphanumericsTransformer().transform(authors);
            for (String author:authors.split(" ")) {
                // shrink each identified author surname to shrinkTo if set
                if (this.shrinkTo != null) author = new StringShrinker(this.shrinkTo).transform(author);
                if (!StringUtils.isBlank(author)) surnames.add(author.trim());
            }
        }
        return StringUtils.join(surnames, " ").toLowerCase();
    }

    public Integer getShrinkTo() {
        return shrinkTo;
    }

    public void setShrinkTo(Integer shrinkTo) {
        this.shrinkTo = shrinkTo;
    }

}
