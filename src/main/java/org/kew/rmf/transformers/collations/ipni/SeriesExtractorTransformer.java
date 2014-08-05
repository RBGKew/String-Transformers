package org.kew.rmf.transformers.collations.ipni;

import org.kew.rmf.transformers.Transformer;

public class SeriesExtractorTransformer implements Transformer{

	@Override
	public String transform(String s) {
		String[] collationElements = CollationUtils.parseCollation(s);
		return collationElements[CollationUtils.SERIES_INDEX];
	}
}
