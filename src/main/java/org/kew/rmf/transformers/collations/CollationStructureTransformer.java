/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers.collations;

import org.kew.rmf.transformers.Transformer;

public class CollationStructureTransformer implements Transformer{

	@Override
	public String transform(String s) {
		return assessCollationStructure(s);
	}

	private static char[] DASHES = {'\u2012', '\u2013', '\u2014'};
	
	// This converts the extended characters to a normalised equivalent which is more easy to work with: 
	// 	accented e 
	// 	emdashes / emrules (sometimes used in place of hyphens).
	private static String convertExtChars(String str){
		for (char c : DASHES)
			while (str.indexOf(c)!=-1)
				str = str.replace(c, '-');
		while (str.indexOf('\u00E9')!=-1)
			str = str.replace('\u00E9', 'e');		
		return str;
	}
	
	public static String[] splitCollation(String collation){
		return convertExtChars(collation).split("[^A-Za-z\u00E90-9\\-]+");
	}
	
	public static String assessCollationStructure(String collation){
		String c_structure = null;
		if (collation != null){
			c_structure  = convertExtChars(collation.toLowerCase());
			c_structure=c_structure.replaceAll("1[7-9][0-9][0-9]","YYYY");
			c_structure=c_structure.replaceAll("20[0-1][0-9]","YYYY");
			c_structure=c_structure.replaceAll("[0-9]+","D");
			c_structure=c_structure.replaceAll("[ivxlc]+","R");
			c_structure=c_structure.replaceAll("[a-z\u00E9]+","A");
			c_structure=c_structure.replaceAll("RA","A");
			c_structure=c_structure.replaceAll("AR","A");
			c_structure=c_structure.replaceAll("A+","A");
			// Treat things like 21A as a "number":
			c_structure=c_structure.replaceAll("DA","D");
			// And treat things like 1891A as a "number":
			c_structure=c_structure.replaceAll("YYYYA","D");			
			// ...and things like 21C as a "number":
			c_structure=c_structure.replaceAll("DR","D");			
			// Treat numeric ranges as a single number:
			c_structure=c_structure.replaceAll("D\\-D","D");
			c_structure=c_structure.toLowerCase();
		}
		return c_structure;
	}
}
