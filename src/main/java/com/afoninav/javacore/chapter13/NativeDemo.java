package com.afoninav.javacore.chapter13;

/**
 * Платформенно-ориентированные методы
 * 1) Делают программу на Java, зависящей
 * от платформы для которой написан этот метод
 * (от ОС и от процессора),
 * т.е. непереносимой, если для другой платформы
 * нет нужной библиотеки.
 * 2) Создают угрозу безопасности, т.к.
 * платформенно ориентированный код не ограничен
 * средой Java.
 *
 *
 */
public class NativeDemo {
    int i;

    public static void main(String[] args) {
        NativeDemo obj = new NativeDemo();
        obj.i = 10;
        System.out.println("Variable i = " + obj.i);
//        obj.test;
        System.out.println("Variable i after native method = " + obj.i);
    }

    // Объявляем платформенно-ориентированный метод
    public native void test();

    // Загружаем библиотеку, содержащую статический метод
    // "NativeDemo.dll" - имя библиотечки для виндовс.
    static {
        System.loadLibrary("NativeDemo");
    }
}

