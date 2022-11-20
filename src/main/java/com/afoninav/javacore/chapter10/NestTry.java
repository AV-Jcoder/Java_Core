package com.afoninav.javacore.chapter10;

public class NestTry {
    public static void main(String[] args) {
        try {
            int a = args.length;
            int b = 42 / a; // деление на ноль если args.length = 0
            System.out.println("a = " + a);

            try {
                if (a == 1) {
                    a = a / (a - a); // деление на ноль
                }
                if (a == 2) {
                    int[] c = {1};
                    c[42] = 99; // выход за пределы массива
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Индекс за пределами массива: " + e);
            }
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль: " + e);
        }
    }
}
