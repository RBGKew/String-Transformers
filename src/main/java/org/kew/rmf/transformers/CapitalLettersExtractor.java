/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers;

/**
 * CapitalLettersExtractor returns only the capital letters in a string.
 *
 * This is achieved by extending {@link A2BTransformer}, where the pattern to
 * search for (`a`) is fixed as "[^A-Z]" (everything but capital letters), and
 * the replacement (`b`) defaults to an empty string but can be overwritten.
 */
public class CapitalLettersExtractor extends A2BTransformer {

    final private String a = "[^A-Z]";
    private String b = " ";

    // Without specifying the getters here Java inheritance would return `a` and
    // `b` of the superclass as the getters are defined there, how strange is
    // that..
    @Override
    public String getA() {
        return this.a;
    }

    @Override
    public String getB() {
        return this.b;
    }

    @Override
    public void setB(String b) {
        this.b = b;
    }
}
