package com.afoninav.javacore.chapter29;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Пример применения потоковых операций
 * Stream Api
 */

public class StreamDemo {
    public static void main(String[] args) {
        // создать динамический массив значений типа Integer
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        System.out.println("Исходный список: " + myList);

        // Получить поток элементов динамического массива
        Stream<Integer> myStream = myList.stream();

        // Получить минимальное и максимальное значение, вызвав
        // методы min(), max(), isPresent() и get()
        Optional<Integer> minVal = myStream.min(Integer::compare);
        if (minVal.isPresent()) {
            System.out.println("Минимальное значение: " + minVal.get());
        }

        // Получить новый поток данных с помощью интерфейса Supplier<T>, поскольку
        // предыдущий вызов метода min() стал терминальной операцией,
        // употребившей (consumed) поток данных.
        Supplier<Stream<Integer>> streamSupplier = myList::stream;
        myStream = streamSupplier.get();
        Optional<Integer> maxVal = myStream.min(Integer::compare);
        maxVal.ifPresent(i -> System.out.println("Максимальное значение: " + i));

        // Отсортировать поток данных вызвав метод sorted()
        Stream<Integer> sortedStream = streamSupplier.get().sorted();

        // Отобразить отсортированный поток данных, вызвав метод forEach()
        System.out.println("Отсортированный поток данных: ");
        sortedStream.forEach(i -> System.out.print(i + " "));
        System.out.println(" ");

        // Вывести на дисплей только нечётные числа
        // вызвав метод filter()
        Stream<Integer> oddVals = streamSupplier.get().filter(n -> n % 2 == 1);
        System.out.println("Нечётные значения: ");
        oddVals.forEach(n -> System.out.print(n + " "));
        System.out.println(" ");

        // Вывести только те нечётные целочисленные значения,
        // которые больше 5. Конвейерные операции фильтрации.
        oddVals = streamSupplier.get().filter(n -> n % 2 == 1).filter(n -> n > 5);
        System.out.println("Нечётные значения больше 5: ");
        oddVals.forEach(n -> System.out.print(n + " "));
        System.out.println(" ");
    }
}
