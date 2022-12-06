package com.afoninav.javacore.chapter15;

import java.util.function.Function;

/**
 * Пример использования предопределённого
 * функционального интерфейса.
 *
 */

public class UseFunctionalInterfaceDemo {
    public static void main(String[] args) {

        // Используем предопределённый функциональный интерфейс
        // и лямбда выражение для вычисления факториала целого цисла
        Function<Integer, Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n ; i++) {
                result *= i;
            }
            return result;
        };

        // Выводим на экран результаты вычислений.
        System.out.println("Factorial of 3 is: " + factorial.apply(3));
        System.out.println("Factorial of 4 is: " + factorial.apply(4));
    }
}
