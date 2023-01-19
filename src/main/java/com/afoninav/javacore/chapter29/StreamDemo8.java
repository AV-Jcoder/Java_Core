package com.afoninav.javacore.chapter29;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Применение итератора в потоке данных
 */

public class StreamDemo8 {
    public static void main(String[] args) {

        // Создать список символьных строк
        ArrayList<String> list = new ArrayList<>();
        list.add("Альфа");
        list.add("Бетта");
        list.add("Гамма");
        list.add("Дельта");
        list.add("Кси");
        list.add("Омега");

        // Получить поток данных
        Stream<String> stream = list.stream();

        // Получить итератор для потока данных
        Iterator<String> iterator = stream.iterator();

        // Перебрать все элементы в потоке данных
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
