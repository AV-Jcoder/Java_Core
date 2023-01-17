package com.afoninav.javacore.chapter29;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Пример оперции сведения, Шилд называет их так,
 * потому что они сводят поток к единственному значению.
 */

public class StreamDemo2 {
    public static void main(String[] args) {

        // Создать список объектов типа Integer
        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(18);
        list.add(10);
        list.add(24);
        list.add(17);
        list.add(5);

        // Перемножить значения списка с помощью метода reduce()
        Optional<Integer> optional = list.stream().reduce((a, b) -> a * b);
        if (optional.isPresent()) {
            System.out.println("Результат вычисления метода reduce(): " + optional.get());
        }

        // То же самое только используем перегруженный метод reduce()
        int reduceResult = list.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Результат вычисления перегруженного метода reduce(): " + reduceResult);

        // Перемножить только чётные числа из листа.
        int reduceResult2 = list.stream().reduce(1,(a, b) -> {
            if (b % 2 == 0) return a * b;
            else return a;
        });
        System.out.println("Результат перемножения чётных чисел из листа с помощью  reduce(): " + reduceResult2);
    }
}








