package org.kew.rmf.transformers.collations.wcs;

import org.kew.rmf.transformers.Transformer;

public class YearExtractorTransformer implements Transformer{

	@Override
	public String transform(String s) {
		String[] collationElements = CollationUtils.parseCollation(s);
		return collationElements[CollationUtils.YEAR_INDEX];
	}
}
