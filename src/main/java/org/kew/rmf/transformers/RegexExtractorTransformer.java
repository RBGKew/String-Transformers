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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A generic transformer that extracts all occurrences of a pattern (regEx)
 * in a string
 *
 * It takes two optional parameters, `removeMultipleWhitespaces` (default true) and
 * `trimIt` (default true)
 */
public class RegexExtractorTransformer extends RegexDefCollection implements Transformer {

    protected String regex = "";
    private boolean removeMultipleWhitespaces = true;
    private boolean trimIt = true;

    @Override
    public String transform(String s) {
    	StringBuffer sb = new StringBuffer();
    	if (s != null){
            if (this.removeMultipleWhitespaces) s = s.replaceAll("\\s+", " ");
            if (this.trimIt) s = s.trim();
	    	Pattern patt = Pattern.compile("(" + regex + ")");
	    	Matcher m = patt.matcher(s);
	    	while (m.find()){
	    		if (sb.length() > 0) sb.append(" ");
	    		sb.append(m.group());
	    	}
    	}
        return sb.toString();
    }

    public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
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