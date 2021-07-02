package com.harload.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 各种奇奇怪怪的测试
 * @author: hcw
 * @create: 2021-06-28 13:39
 */
public class Test {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        List<String> filterList = stringList.stream().filter(item -> item.equals("1")).collect(Collectors.toList());
        filterList.forEach(System.out::printf);
    }

}
