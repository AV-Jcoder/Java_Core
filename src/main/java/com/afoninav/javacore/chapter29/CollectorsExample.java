package com.afoninav.javacore.chapter29;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 *  https://www.baeldung.com/java-8-collectors
 */

public class CollectorsExample {

    public static void main(String[] args) {

        List<String> givenList =            Arrays.asList("a", "bb", "ccc", "dd");
        List<String> listWithDuplicates =   Arrays.asList("a", "bb", "ccc", "dd", "bb");
        List<Integer> numbers =             Arrays.asList(42, 4, 2, 24);


        // Collectors.toList()
        List<String> unknownList = givenList.stream()
                .collect(toList());
        System.out.println("Type of collected list : " + unknownList.getClass());

        ArrayList<Object> arrayList = givenList.stream()
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);

        // Collectors.toUnmodifiableList()
        List<String> collect1 = givenList.stream()
                .collect(toUnmodifiableList());

        try{
            collect1.add("Hello");
        } catch (UnsupportedOperationException e) {
            System.err.println("\nCollectors return unmodifiable list.");
            e.printStackTrace();
        }

        // Collectors.toSet()
        Set<String> set1 = givenList.stream()
                .collect(toSet());
        int size = set1.size();
        System.out.println("Set1 from givenList:             " + set1 + ". Size: " + size);

        Set<String> set2 = listWithDuplicates.stream()
                .collect(toSet());
        size = set2.size();
        System.out.println("Set2 from list with duplicates : " + set2 + ". Size: " + size);

        // Collectors.toUnmodifiedSet()
        Set<String> setUnmodif = givenList.stream()
                .collect(toUnmodifiableSet());
        try {
            setUnmodif.add("Hello");
        } catch (UnsupportedOperationException e) {
            System.err.println("\nCollectors return unmodifiable set.");
            e.printStackTrace();
        }

        // Collectors.toCollection()
        List<String> linkedList = givenList.stream()
                .collect(toCollection(LinkedList::new));
        System.out.println("Stream to Collection - linked list. result: " + linkedList);

        // Collectors.toMap()
        Map<String, Integer> map1 = givenList.stream()
                .collect(toMap(Function.identity(), String::length));
        System.out.println("Stream to map. result: " + map1);

        try {
            Map<String, Integer> map2 = listWithDuplicates.stream()
                    .collect(toMap(Function.identity(), String::length));
        } catch (IllegalStateException e) {
            System.err.println("\nCollision error!!!");
            e.printStackTrace();
        }


        Map<String, Integer> map3 = listWithDuplicates.stream()
                .collect(toMap(Function.identity(), String::length, (a, b) -> a));
        System.out.println("Stream with duplicate to map. Collision error canceled: " + map3);

        // Collectors.toUnmodifiableMap()
        Map<String, Integer> unmodifableMap = givenList.stream()
                .collect(toUnmodifiableMap(Function.identity(), String::length));
        try {
            unmodifableMap.put("add error", "add error".length());
        } catch (UnsupportedOperationException e) {
            System.err.println("\nUnmodifiable map!!!");
            e.printStackTrace();
        }

        // Collectors.collectingAndThen()
        Set<String> setString = givenList.stream()
                .collect(collectingAndThen(toList(), localList -> localList.stream()
                        .map(str -> str + "-Hello!")
                        .collect(toSet())));
        System.out.println("Collect to list and then to set: " + setString);

        // Collectors.joining()
        String joiningString = givenList.stream()
                .collect(joining());
        System.out.println("Result of joining: " + joiningString);

        String joiningString2 = givenList.stream()
                .collect(joining(" : ", "[", "]"));
        System.out.println("Result of joining: " + joiningString2);

        // Collectors.counting()
        DoubleSummaryStatistics stat = givenList.stream()
                .collect(summarizingDouble(String::length));
        System.out.println("Statistics of string length : " + stat);

        // Collectors.averagingDouble/Long/Int()
        Double avgLength = givenList.stream()
                .collect(averagingDouble(String::length));
        System.out.println("Avg length : " + avgLength);

        // Collectors.summingDouble/Long/Int()
        Double strLengthSumming = givenList.stream()
                .collect(summingDouble(String::length));
        System.out.println("Summing of strings length : " + strLengthSumming);

        // Collectors.maxBy()/minBy()
        Optional<String> maxValue = givenList.stream()
                .collect(maxBy(Comparator.naturalOrder()));
        System.out.println("Max value of stream: " + maxValue.orElseGet(() -> "Null value."));

        // Collectors.groupingBy()
        Map<Integer, Set<String>> groupingBy = listWithDuplicates.stream()
                .collect(groupingBy(String::length, toSet()));
        System.out.println("Группировка коллекции в Map<Integer, Set<String>> : " + groupingBy);

        // Collectors.partitioningBy()
        Map<Boolean, List<String>> stringLenMoreThenTwo = listWithDuplicates.stream()
                .collect(partitioningBy(str -> str.length() > 2));
        System.out.println("Разделение строк в мапе на два типа: " + stringLenMoreThenTwo);

        // Collectors.teeing()
        Integer collect = numbers.stream().collect(teeing(
                minBy(Integer::compareTo), // The first collector
                maxBy(Integer::compareTo), // The second collector
                (min, max) -> min.get() + max.get()
        ));
        System.out.println(collect);

        // Custom Collectors



        System.out.println("End main");
    }
}

















