package org.kew.rmf.transformers.collations.ipni;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PageExtractorTransformerTest {

		   private String collation;
		   private String  expectedPage;
		   
		   // Each parameter should be placed as an argument here
		   // Every time runner triggers, it will pass the arguments
		   // from parameters we defined in collations() method
		   public PageExtractorTransformerTest(String collation
				   , String expectedPage
				   ) {
		      this.collation = collation;
		      this.expectedPage=expectedPage;
		   }

		   
		   @Parameterized.Parameters
		   public static Collection<?> collations() {
		      //return CollationTestData.collations();
			   return CollationTestData.collationsAndPages();
		   }
		   
		   @Test
		   public void testCollationParsing() {
		      System.out.println("Collation is : " + collation 
		    		  + ", page: " + expectedPage
		    		  );
		      String parsedPage = new PageExtractorTransformer().transform(collation);
		      assertEquals(expectedPage, parsedPage);
		   }
}