package org.kew.rmf.transformers.collations.ipni;

import org.kew.rmf.transformers.Transformer;

public class IssueExtractorTransformer implements Transformer{

	@Override
	public String transform(String s) {
		String[] collationElements = CollationUtils.parseCollation(s);
		return collationElements[CollationUtils.ISSUE_INDEX];
	}
}
