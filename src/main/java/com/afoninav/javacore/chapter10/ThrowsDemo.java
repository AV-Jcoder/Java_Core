package com.afoninav.javacore.chapter10;

/**
 * Пример программы генерирующей исключение
 * без указания об этом.
 * Ошибка компиляции.
 */

public class ThrowsDemo {
    static void throwOne() {
        System.out.println("Внутри тела метода throwOne().");
        throw new IllegalAccessException("Demonstration");
    }

    public static void main(String[] args) {
        throwOne();
    }


}
