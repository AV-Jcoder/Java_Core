package com.afoninav.javacore.chapter29;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * Применение Сплитератора в потоке данных
 * для перебора знчаений Коллекции
 */

public class StreamDemo9 {
    public static void main(String[] args) {

        // Создать Коллекцию строк
        ArrayList<String> list = new ArrayList<>();
        list.add("Альфа");
        list.add("Бетта");
        list.add("Гамма");
        list.add("Дельта");
        list.add("Кси");
        list.add("Омега");

        // Получить поток данных
        Stream<String> stream = list.stream();

        // Получить Сплитератор
        Spliterator<String> spliterator = stream.spliterator();

        // Перебрать элементы в потоке данных
        while (spliterator.tryAdvance(System.out::println));
    }
}
