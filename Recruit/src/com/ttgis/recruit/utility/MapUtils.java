/**
 * 董再兴 MapUtils.java 2013年7月3日
 */
package com.ttgis.recruit.utility;

import java.util.Map;

/**
 * Map工具类
 */
public class MapUtils {
	@SuppressWarnings("all")
	public static void putIfNull(Map map,Object key,Object defaultValue) {
		if(key == null)
			throw new IllegalArgumentException("key must be not null");
		if(map == null)
			throw new IllegalArgumentException("map must be not null");
		if(map.get(key) == null) {
			map.put(key, defaultValue);
		}
	}
	
}
