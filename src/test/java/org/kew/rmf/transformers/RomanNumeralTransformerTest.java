package org.kew.rmf.transformers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.kew.rmf.transformers.RomanNumeralTransformer;
import org.kew.rmf.transformers.Transformer;

public class RomanNumeralTransformerTest {

    @Test
    public void test() throws Exception {
        Transformer transformer = new RomanNumeralTransformer();
        assertEquals("1 1", transformer.transform("i i"));
        assertEquals("fig", transformer.transform("fig"));
        assertEquals("109", transformer.transform("CIX"));
        assertEquals("1009", transformer.transform("MIX"));
    }

}
