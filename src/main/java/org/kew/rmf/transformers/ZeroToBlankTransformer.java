package org.kew.rmf.transformers;

/**
 * This transformer translates zeros to blanks
 */
public class ZeroToBlankTransformer implements Transformer{

	@Override
	public String transform(String s) {
		String transformed = s;
		if ("0".equals(s)) {
			transformed = "";
		}
		return transformed;
	}
}
