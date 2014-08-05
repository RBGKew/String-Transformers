package org.kew.rmf.transformers;


/**
 * This transformer strips non numeric characters
 */
public class StripNonNumericCharactersTransformer extends A2BTransformer {
	
	final private String a = "[^0-9]";
	final private String b = " ";

	@Override
	public String getA() {
		return this.a;
	}

	@Override
	public String getB() {
		return this.b;
	}
}