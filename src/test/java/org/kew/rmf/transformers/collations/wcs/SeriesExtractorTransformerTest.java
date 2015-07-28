package org.kew.rmf.transformers.collations.wcs;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class SeriesExtractorTransformerTest {

		   private String collation;
		   private String  expectedSer;
		   
		   // Each parameter should be placed as an argument here
		   // Every time runner triggers, it will pass the arguments
		   // from parameters we defined in collations() method
		   public SeriesExtractorTransformerTest(String collation
				   , String expectedSer
				   ) {
		      this.collation = collation;
		      this.expectedSer=expectedSer;
		   }

		   @Parameterized.Parameters
		   public static Collection<?> collations() {
		      return CollationTestData.collationsAndSeries();
		   }
		   
		   @Test
		   public void testCollationParsing() {
		      System.out.println("Collation is : " + collation 
		    		  + ", series: " + expectedSer
		    		  );
		      String parsedSeries= new SeriesExtractorTransformer().transform(collation);
		      assertEquals(expectedSer, parsedSeries);
		   }
}