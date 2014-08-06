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
 * This transformer canonicalises epithets
 * @author nn00kg
 *
 */
public class EpithetTransformer implements Transformer {

	@Override
	public String transform(String s) {
		  if (s != null){
			  s = s.replaceAll("[^A-Za-z]", "");
			  s = s.replaceFirst("(?<=[^i])ana$", "iana");
			  s = s.replaceFirst("aef", "if");
			  s = s.replaceFirst("colus$", "cola");
			  s = s.replaceFirst("us$", "a");
			  s = s.replaceFirst("um$", "a");
			  s = s.replaceFirst("on$", "a");
			  s = s.replaceFirst("iae$", "i");
			  s = s.replaceFirst("ei$", "i");
			  s = s.replaceFirst("ae$", "i");
			  s = s.replaceFirst("ii$", "i");
			  s = s.replaceFirst("ioi$", "oi");
			  s = s.replace("j", "i");
			  s = s.replace("y", "i");
			  s = s.replace("-", "");
			  s = s.replace("'", "");
			  /*
			  if (value.endsWith("anus") && !value.endsWith("ianus"))
				  value = value.replaceFirst("anus$", "ianus");
			  if (value.endsWith("anum") && !value.endsWith("ianum"))
				  value = value.replaceFirst("anum$", "ianum");
			  if (value.endsWith("arum") && !value.endsWith("iarum"))
				  value = value.replaceFirst("arum$", "iarum");
			  if (value.endsWith("orum") && !value.endsWith("iorum"))
				  value = value.replaceFirst("orum$", "iorum");
				*/
		  }
	    return s;
	}

}