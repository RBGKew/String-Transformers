package org.kew.rmf.transformers.collations.ipni;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class IssueExtractorTransformerTest {

		   private String collation;
		   private String  expectedIssue;
		   
		   // Each parameter should be placed as an argument here
		   // Every time runner triggers, it will pass the arguments
		   // from parameters we defined in collations() method
		   public IssueExtractorTransformerTest(String collation
				   , String expectedIssue
				   ) {
		      this.collation = collation;
		      this.expectedIssue=expectedIssue;
		   }

		   @Parameterized.Parameters
		   public static Collection<?> collations() {
		      return CollationTestData.collationsAndIssues();
		   }
		   
		   @Test
		   public void testCollationParsing() {
		      System.out.println("Collation is : " + collation 
		    		  + ", issue: " + expectedIssue
		    		  );
		      String parsedIssue = new IssueExtractorTransformer().transform(collation);
		      assertEquals(expectedIssue, parsedIssue);
		   }
}