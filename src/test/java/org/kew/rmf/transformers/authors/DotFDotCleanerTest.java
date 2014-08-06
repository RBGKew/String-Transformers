/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers.authors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kew.rmf.transformers.Transformer;
import org.kew.rmf.transformers.authors.DotFDotCleaner;

public class DotFDotCleanerTest {

    @Test
    public void cleanIt() throws Exception {
        Transformer transformer = new DotFDotCleaner();
        String author = "Hans.f. bla";
        assertEquals("Hans bla", transformer.transform(author));
        author = "T.Anderson ex Hook.f.";
        assertEquals("T.Anderson ex Hook", transformer.transform(author));
        author = "Kauffman";
        assertEquals("Kauffman", transformer.transform(author));
    }

    @Test
    public void alsoCleanSpaceFDot() throws Exception {
        Transformer transformer = new DotFDotCleaner();
        String author = "Hans f. bla";
        assertEquals("Hans bla", transformer.transform(author));
        author = "Baker f.";
        assertEquals("Baker", transformer.transform(author));
    }

}
