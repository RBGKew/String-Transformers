package org.kew.rmf.utils;

import java.util.Map;
import java.util.Set;

/**
 * A Dictionary is a Map of String to String.
 */
public interface Dictionary {
	public Set<Map.Entry<String,String>> entrySet();
	public String get(String key);
}
