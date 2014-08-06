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
import org.kew.rmf.transformers.authors.ShrunkPubAuthors;

public class ShrunkPubAuthorsTest {

    @Test
    public void shrinkEm() throws Exception {
        Transformer transformer = new ShrunkPubAuthors();
        String author = "(A.B.Cyclus ex D.E. Fincus) G.H. Ictus.f.";
        assertEquals("ictus", transformer.transform(author));
        author = "T.Anderson ex Hook.f.";
        assertEquals("hook", transformer.transform(author));
    }

    @Test
    public void shrinkEmSmaller() {
        ShrunkPubAuthors transformer = new ShrunkPubAuthors();
        String author = "(A.B.Cyclus ex D.E. Fincus) G.H. Ictus.f.";
        assertEquals("ictus", transformer.transform(author));
        transformer.setShrinkTo(3);
        assertEquals("ict", transformer.transform(author));
    }

    @Test
    public void weirdCasesByRachel() {
        ShrunkPubAuthors transformer = new ShrunkPubAuthors();
        transformer.setShrinkTo(3);
        String author = "Bob,       Tony & John in Smith";
        assertEquals("bob ton joh", transformer.transform(author));
        author = "A.DC.";
        assertEquals("dc", transformer.transform(author));
        author = "Ă.Löve & D.Löve";
        assertEquals("lov lov", transformer.transform(author));
        author = "Benth.) Verdc.";
        assertEquals("ben ver", transformer.transform(author));
        author = "F.jr. ex Hook.f.";
        assertEquals("hoo", transformer.transform(author));
    }

    @Test
    public void hyphenProblems() {
        ShrunkPubAuthors transformer = new ShrunkPubAuthors();
        transformer.setShrinkTo(3);
        String author = "Hand.-Mazz.";
        assertEquals("han maz", transformer.transform(author));
        author = "Smith ex J.-F.Leroy";
        assertEquals("ler", transformer.transform(author));
        author = "A.St.-Hil.";
        assertEquals("st hil", transformer.transform(author));
        author = "B.-E.van Wyk in Smyth";
        assertEquals("van wyk", transformer.transform(author));
        author = "Grey-Wilson & Banks";
        assertEquals("gre wil ban", transformer.transform(author));
    }

    @Test public void additionalCases() {
        ShrunkPubAuthors transformer = new ShrunkPubAuthors();
        transformer.setShrinkTo(3);
        String author = "L. Ex Somebody";
        assertEquals("som", transformer.transform(author));
        author = "Hand.-Mazz";
        assertEquals("han maz", transformer.transform(author));

    }

}
