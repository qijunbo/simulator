package org.simulator.common;

import java.util.HashMap;
import java.util.Map;

public class Cache {

	private static final Map<Object, Object> map = new HashMap<Object, Object>();

	public static Object get(Object key) {
		return map.get(key);
	}

	public static Object put(Object key, Object value) {
		return map.put(key, value);
	}

	public static Object remove(Object key) {
		return map.remove(key);
	}

}
