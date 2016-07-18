package com.phoenixgjh.volleybase.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 对map按字母顺序进行排序
 * Created by Phoenix on 2016/7/14.
 */
public class MapUtil {
    /**
     * 根据字母书序对key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMap(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new TreeMap<>(new MapKeyComparator());
        sortedMap.putAll(map);
        return sortedMap;
    }

    public static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }
}
