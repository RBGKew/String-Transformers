/*
 * Copyright © 2012, 2013, 2014 Royal Botanic Gardens, Kew.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.kew.rmf.transformers.collations.ipni;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kew.rmf.transformers.collations.ipni.CollationUtils;

@RunWith(Parameterized.class)
public class CollationUtilsTest {

	   private String collation;
	   private String  expectedSer;
	   private String  expectedVol;
	   private String  expectedIssue;
	   private String  expectedPage;
	   private String  expectedTabOrFig;
	   private String  expectedYear;
	   private String  expectedRule;
	   
	   // Each parameter should be placed as an argument here
	   // Every time runner triggers, it will pass the arguments
	   // from parameters we defined in collations() method
	   public CollationUtilsTest(String collation
			   , String expectedSer
			   , String expectedVol
			   , String expectedIssue
			   , String expectedPage
			   , String expectedTabOrFig
			   , String expectedYear
			   , String expectedRule
			   ) {
	      this.collation = collation;
	      this.expectedSer= expectedSer;
	      this.expectedVol = expectedVol;
	      this.expectedIssue=expectedIssue;
	      this.expectedPage=expectedPage;
	      this.expectedTabOrFig=expectedTabOrFig;
	      this.expectedYear=expectedYear;
	      this.expectedRule=expectedRule;
	   }

	   @Parameterized.Parameters
	   public static Collection<?> collations() {
	      return CollationTestData.collations();
	   }

	   @Test
	   public void testCollationParsing() {
		  /*
	      System.out.println("Collation is : " + collation 
	    		  + ", series: " + expectedSer 
	    		  + ", volume: " + expectedVol
	    		  + ", issue: " + expectedIssue
	    		  + ", page: " + expectedPage
	    		  + ", tab_or_fig: " + expectedTabOrFig
	    		  + ", year: " + expectedYear
	    		  + ", rule: " + expectedRule
	    		  );
	    	*/
	      String[] parsedCollation =  CollationUtils.parseCollation(collation);
	      assertEquals(expectedSer, parsedCollation[CollationUtils.SERIES_INDEX]);
	      assertEquals(expectedVol, parsedCollation[CollationUtils.VOL_INDEX]);
	      assertEquals(expectedIssue, parsedCollation[CollationUtils.ISSUE_INDEX]);
	      assertEquals(expectedPage, parsedCollation[CollationUtils.PAGE_INDEX]);
	      assertEquals(expectedTabOrFig, parsedCollation[CollationUtils.TAB_OR_FIG_INDEX]);
	      assertEquals(expectedYear, parsedCollation[CollationUtils.YEAR_INDEX]);
	      assertEquals(expectedRule, parsedCollation[CollationUtils.RULE_INDEX]);

	      if (parsedCollation[CollationUtils.RULE_INDEX].equals(""))
	    	  System.out.println("Did not parse: " + collation);
	      
	      //assertNotSame("", parsedCollation[CollationUtils.RULE_INDEX]);
	      
	   }
	}