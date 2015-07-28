package org.kew.rmf.transformers.collations.wcs;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class VolExtractorTransformerTest {

		   private String collation;
		   private String  expectedVol;
		   
		   // Each parameter should be placed as an argument here
		   // Every time runner triggers, it will pass the arguments
		   // from parameters we defined in collations() method
		   public VolExtractorTransformerTest(String collation
				   , String expectedVol
				   ) {
		      this.collation = collation;
		      this.expectedVol=expectedVol;
		   }

		   @Parameterized.Parameters
		   public static Collection<?> collations() {
		      return CollationTestData.collationsAndVols();
		   }
		   
		   @Test
		   public void testCollationParsing() {
		      System.out.println("Collation is : " + collation 
		    		  + ", vol: " + expectedVol
		    		  );
		      String parsedVol = new VolExtractorTransformer().transform(collation);
		      assertEquals(expectedVol, parsedVol);
		   }
}