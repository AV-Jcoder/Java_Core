package com.afoninav.javacore.chapter10;

/**
 * Пример программы генерирующей исключение
 * без указания об этом.
 * Ошибка компиляции.
 *
 * Добавлен оператор throws в объявлении метода бросающего исключение
 * и блок try/catch в методе main для обработки этого исключения.
 *
 */

public class ThrowsDemo {
    static void throwOne() throws IllegalAccessException {
        System.out.println("Внутри тела метода throwOne().");
        throw new IllegalAccessException("Demonstration");
    }

    public static void main(String[] args) {
        try {
            throwOne();
        } catch (IllegalAccessException e) {
            System.out.println("Перехвачено исключение: " + e);
        }
    }
}
