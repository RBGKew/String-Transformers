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
 * A generic transformer that searches for all occurrences of a string
 * {@link #a} and replaces each with a string {@link #b}.
 * <br/>
 * It takes two optional parameters, {@link #removeMultipleWhitespaces} (default true) and
 * {@link #trimIt} (default true)
 */
public class A2BTransformer implements Transformer {

	private String a = "";
	private String b = "";
	private boolean removeMultipleWhitespaces = true;
	private boolean trimIt = true;

	@Override
	public String transform(String s) {
		if (s == null) return null;

		s = s.replace(getA(), getB());

		if (this.removeMultipleWhitespaces) {
			s = SqueezeWhitespaceTransformer.MULTIPLE_WHITESPACE.matcher(s).replaceAll(" ");
		}

		if (this.trimIt) {
			s = s.trim();
		}

		return s;
	}

	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}

	public boolean isRemoveMultipleWhitespaces() {
		return removeMultipleWhitespaces;
	}
	public void setRemoveMultipleWhitespaces(boolean removeMultipleWhitespaces) {
		this.removeMultipleWhitespaces = removeMultipleWhitespaces;
	}

	public boolean isTrimIt() {
		return trimIt;
	}
	public void setTrimIt(boolean trimIt) {
		this.trimIt = trimIt;
	}
}
