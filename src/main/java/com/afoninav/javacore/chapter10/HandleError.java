package com.afoninav.javacore.chapter10;

import java.util.Random;

public class HandleError {
    public static void main(String[] args) {
         int a = 0, b = 0, c = 0;
        Random r = new Random();
        for (int i = 0; i < 32000; i++) {
            try {
                c = r.nextInt();
                b = r.nextInt();
                a = 12345 / (b / c);
            } catch (ArithmeticException e) {
                System.out.println("Деление на ноль");
                a = 0;
            }
            System.out.println("a = " + a);
        }
    }
}
