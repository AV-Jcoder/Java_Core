package com.afoninav.javacore.chapter10;

/**
 * Демонстрация работы оператора throw.
 */

public class ThrowDemo {
    static void demoproc() {
        try {
            throw new NullPointerException("Демонстрация");
        } catch (NullPointerException e) {
            System.out.println("Исключение перехвачено в теле метода demoproc().");
            throw e; // повторная генерация исключения
        }
    }

    public static void main(String[] args) {
        try {
            demoproc();
        } catch (NullPointerException e) {
            System.out.println("Повторный перехват в main(): " + e);
        }
    }
}
