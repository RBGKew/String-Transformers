package org.kew.rmf.transformers.collations.wcs;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class TabOrFigExtractorTransformerTest {

		   private String collation;
		   private String  expectedTabOrFig;
		   
		   // Each parameter should be placed as an argument here
		   // Every time runner triggers, it will pass the arguments
		   // from parameters we defined in collations() method
		   public TabOrFigExtractorTransformerTest(String collation
				   , String expectedTabOrFig
				   ) {
		      this.collation = collation;
		      this.expectedTabOrFig=expectedTabOrFig;
		   }

		   @Parameterized.Parameters
		   public static Collection<?> collations() {
		      return CollationTestData.collationsAndTabOrFig();
		   }
		   
		   @Test
		   public void testCollationParsing() {
		      System.out.println("Collation is : " + collation 
		    		  + ", tab_or_fig: " + expectedTabOrFig
		    		  );
		      String parsedTabOrFig= new TabOrFigExtractorTransformer().transform(collation);
		      assertEquals(expectedTabOrFig, parsedTabOrFig);
		   }
}