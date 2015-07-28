package org.kew.rmf.transformers.collations.wcs;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class YearExtractorTransformerTest {

		   private String collation;
		   private String  expectedYear;
		   
		   // Each parameter should be placed as an argument here
		   // Every time runner triggers, it will pass the arguments
		   // from parameters we defined in collations() method
		   public YearExtractorTransformerTest(String collation
				   , String expectedYear
				   ) {
		      this.collation = collation;
		      this.expectedYear=expectedYear;
		   }

		   @Parameterized.Parameters
		   public static Collection<?> collations() {
		      return CollationTestData.collationsAndYears();
		   }
		   
		   @Test
		   public void testCollationParsing() {
		      System.out.println("Collation is : " + collation 
		    		  + ", year: " + expectedYear
		    		  );
		      String parsedYear = new YearExtractorTransformer().transform(collation);
		      assertEquals(expectedYear, parsedYear);
		   }
}