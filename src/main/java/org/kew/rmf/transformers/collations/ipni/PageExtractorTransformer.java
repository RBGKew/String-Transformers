package org.kew.rmf.transformers.collations.ipni;

import org.kew.rmf.transformers.Transformer;

public class PageExtractorTransformer implements Transformer{

	@Override
	public String transform(String s) {
		String[] collationElements = CollationUtils.parseCollation(s);
		return collationElements[CollationUtils.PAGE_INDEX];
	}
}