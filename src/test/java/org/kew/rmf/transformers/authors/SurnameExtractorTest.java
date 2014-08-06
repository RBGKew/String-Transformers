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
import org.kew.rmf.transformers.authors.SurnameExtractor;

public class SurnameExtractorTest {

    @Test
    public void extractSimple() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "(R.W.Pohl) P.M.Peterson";
        assertEquals("(Pohl) Peterson", transformer.transform(author));
    }

    @Test
    public void extractNoBasionymNoSpaceBetweenFirstAbbrSurname() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "R.Goaverts";
        assertEquals("Goaverts", transformer.transform(author));
    }

    @Test
    public void extractNoBasionymSpaceBetweenFirstAbbrSurname() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "R. Goaverts";
        assertEquals("Goaverts", transformer.transform(author));
    }

    @Test
    public void extractNoBasionymTwoFirstAbbrvs() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "A.B.Cedrus";
        assertEquals("Cedrus", transformer.transform(author));
        author = "Hochst. ex A.Rich.";
        assertEquals("Hochst. ex Rich.", transformer.transform(author));
    }

    @Test
    public void extractShortSurname() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "(R.W.P.) L.";
        assertEquals("(P.) L.", transformer.transform(author));
    }

    @Test
    public void extractAbbrevSurname() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "(R.W.Po.) Ludov.";
        assertEquals("(Po.) Ludov.", transformer.transform(author));
    }

    @Test
    public void dealWithHyphenatedNames() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "J.-C.Belmondo & H.-J.Bladibla";
        assertEquals("Belmondo & Bladibla", transformer.transform(author));
        author = "E. Wainwright-Deri & F.-S.Schmidt-Soltau";
        assertEquals("Wainwright-Deri & Schmidt-Soltau", transformer.transform(author));
    }

    @Test
    public void additionalCases() throws Exception {
        Transformer transformer = new SurnameExtractor();
        String author = "L. ex Somebody";
        assertEquals("L. ex Somebody", transformer.transform(author));
        author = "L. in Somebody";
        assertEquals("L. in Somebody", transformer.transform(author));
        //author = "Kosnik, Diggs, Redshaw & L.L.Lipscomb";
        //assertEquals("Lipscomb", transformer.transform(author));
        author = "L.Diggs";
        assertEquals("Diggs", transformer.transform(author));
    }

}
