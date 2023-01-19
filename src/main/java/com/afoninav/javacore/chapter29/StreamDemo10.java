package com.afoninav.javacore.chapter29;

import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Пример использования метода split()
 * для объекта типа Spliterator
 */

public class StreamDemo10 {
    public static void main(String[] args) {

        // Создадим поставщика стримов.
        Supplier<Stream<String>> supplier = () -> Stream.of(
                "Альфа",
                "Бетта",
                "Гамма",
                "Дельта",
                "Кси",
                "Омега"
        );

        // Создадим стрим.
        Stream<String> stream = supplier.get();

        // Создадим сплитератор.
        Spliterator<String> spliterator = stream.spliterator();

        // Попробуем разделить объект spliterator на два объекта.
        Spliterator<String> spliterator2 = spliterator.trySplit();

        // Пробуем воспользоваться полученным Сплитератором2.
        if (spliterator2 != null) {
            System.out.println("Результат работы метода forEachRemaining() объекта spliterator2: ");
            spliterator2.forEachRemaining(System.out::println);
            System.out.println();
        }

        // А теперь воспользуемся первым Сплитератором
        System.out.println("Результат выводимый первым сплитератором: ");
        spliterator.forEachRemaining(System.out::println);
    }
}
