package com.tm.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class SortUtil {

    public static <K, V extends Comparable<? super V>> Map<K, V> compareByValueDesc(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;

    }

    public static <K, V extends Comparable<? super V>> Map<K, V> compareByValue(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;

    }

}
