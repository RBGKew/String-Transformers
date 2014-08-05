package org.kew.rmf.transformers;


/**
 * Deletes X and x that seem to be meant as hybrid signs
 *
 * X and x can be at the beginning of a string followed by a whitespace or
 * anywhere in the string if surrounded by white-spaces.
 */
public class FakeHybridSignCleaner extends A2BTransformer {

	final private String a = "^[Xx]\\s|\\s[xX]\\s";
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
