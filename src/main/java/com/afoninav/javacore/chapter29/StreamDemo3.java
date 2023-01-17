package com.afoninav.javacore.chapter29;

import java.util.ArrayList;

/**
 * Пример работы с параллельными потоками данных.
 * Вычисляется произведение квадратных корней
 * хранящихся в листе.
 */

public class StreamDemo3 {
    public static void main(String[] args) {

        // Создать список числовых значчений типа Double.
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(7.0);  // 2.64
        list.add(18.0); // 4.24
        list.add(10.0); // 3.16
        list.add(24.0); // 4.89
        list.add(17.0); // 4.12
        list.add(5.0);  // 2.23

        // Обычное вычисление произведения квадратных корней
        double result = list.stream().reduce(1.0, (a, b) -> a * Math.sqrt(b));
        System.out.println("Результат произведения квадратных корней обычным способом: " + result);

        // Правильное параллельное вычисление
        double productOfSqrRoots = list.parallelStream().reduce(1.0,
                                                                (a, b) -> a * Math.sqrt(b),
                                                                (a, b) -> a * b); // result * result
        System.out.println("Произведение квадратных корней параллельными потоками: " + productOfSqrRoots);

        // Ошибочное параллельное вычисление
        double productOfSqrRoots2 = list.parallelStream().reduce(1.0,
                (a, b) -> a * Math.sqrt(b));
        System.out.println("Ошибочное вычисление параллельными потоками: " + productOfSqrRoots2);
    }
}
