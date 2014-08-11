/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers.botany;

import static org.junit.Assert.*;

import org.junit.Test;
import org.kew.rmf.transformers.Transformer;
import org.kew.rmf.transformers.botany.FakeHybridSignCleaner;

public class FakeHybridSignCleanerTest {

    @Test
    public void test() throws Exception {
        Transformer transformer = new FakeHybridSignCleaner();
        assertEquals("bladibla", transformer.transform("X bladibla"));
        assertEquals("bladibla", transformer.transform("x bladibla"));
        assertEquals("bladi bla", transformer.transform("bladi x bla"));
        assertEquals("bladi bla", transformer.transform("bladi X bla"));

        assertEquals("bladix bla", transformer.transform("bladix bla"));
        assertEquals("bladiX bla", transformer.transform("bladiX bla"));
    }

}
