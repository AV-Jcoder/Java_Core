package com.afoninav.javacore.chapter10;

/**
 * Пример с оператором try, вложенным в метод.
 */
public class MethNestTry {
    static void nesttry(int a) {
        try {
            if (a == 1) a = a/ (a - a); // деление на ноль
            if (a == 2) {
                int[] c = {1};
                c[42] = 99; // выход за пределы массива
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс за пределами массива: " + e);
        }
    }

    public static void main(String[] args) {
        try {
            int a = args.length;
            int b = 42 / a;
            System.out.println("a = " + a);
            nesttry(a);
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль: " + e);
            e.printStackTrace();
        }
    }
}
