package com.afoninav.javacore.chapter29;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Пример отображения потока Stream на поток IntStream.
 *
 */

public class StreamDemo6 {
    public static void main(String[] args) {

        // Получить список значений типа Double
        ArrayList<Double> list = new ArrayList<>();
        list.add(1.1);
        list.add(3.6);
        list.add(9.2);
        list.add(4.7);
        list.add(12.1);
        list.add(5.0);

        System.out.println("Исходные значения из списка: ");
        list.stream().forEach(var -> System.out.print("[" + var + "] "));
        System.out.println("\n");

        // Отобразить поток Stream<Double> на поток IntStream,
        // с максимально допустимым пределом для каждого значения.
        IntStream intStream = list.stream().mapToInt((var) -> (int) Math.ceil(var));
        System.out.println("Данные после отображения: ");
        intStream.forEach(var -> System.out.print("[" + var + "] "));
    }
}
