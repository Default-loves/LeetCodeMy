package com.junyi.stream;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @time: 2020/8/14 20:38
 * @version: 1.0
 * @author: junyi Xu
 * @description: List 转变为 Map
 */
public class List2Map {
    public static void main(String[] args) {
        // 1. 只配置了Key和Value
        Stream<String> data1 = Stream.of("apple", "banana", "orange");
        ConcurrentMap<Character, String> concurrentMap1 = data1.collect(Collectors.toConcurrentMap(
                s1 -> s1.charAt(0), String::toUpperCase));
        System.out.println(concurrentMap1);

        // 2. 还配置了Key冲突处理
        Stream<String> data2 = Stream.of("apple", "banana", "apricot", "orange");
        ConcurrentMap<Character, String> concurrentMap2 = data2.collect(Collectors.toConcurrentMap(
                s1 -> s1.charAt(0), String::toUpperCase, (s1, s2) -> s1 + "|" + s2));
        System.out.println(concurrentMap2);

        // 3. 第四个参数作用未知
        Stream<String> data3 = Stream.of("apple", "banana", "apricot", "orange");
        ConcurrentMap<Character, String> concurrentMap3 = data3.collect(Collectors.toConcurrentMap(
                s1 -> s1.charAt(0), String::toUpperCase, (s1, s2) -> s1 + "|" + s2, ConcurrentHashMap::new));
        System.out.println(concurrentMap3);

    }
}
