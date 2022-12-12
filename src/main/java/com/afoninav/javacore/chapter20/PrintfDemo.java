package com.afoninav.javacore.chapter20;

/**
 * Пример применения метода printf() класса PrintStream
 */

public class PrintfDemo {
    public static void main(String[] args) {
        System.out.println("Ниже приведены числовые ззначения в различных форматах. \n");

        System.out.printf("Разные целочисленные форматы: ");
        System.out.printf("%d % (d + %+d %05d \n)", 3,-3,3,3);

        System.out.println("");

        System.out.printf("Формат чисел с плавающие точкой " +
                " по умолчанию: %f\n", 123456.123);

        System.out.printf("Формат чисел с плавающие точкой, " +
                " разделяемых запятыми: %,f\n", 123456.123);

        System.out.printf("Формат отрицательных чисел с плавающие точкой " +
                " по умолчанию: %f\n", -123456.123);

        System.out.printf("Формат отрицательных чисел с плавающие точкой " +
                " разделяемых запятыми: %,f\n", -123456.123);

        System.out.printf("Дpyгoй Формат отрицательных чисел с " +
        "плавающей точкой : %,(f\n", -1234567.123);

        System.out.println("Выравнивание положительных и отрицательных числовых значений:");
        System.out.printf("% ,.2f\n% ,.2f\n",123456.123,-123456.123);


    }
}
