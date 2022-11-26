package com.afoninav.javacore.chapter15;

public class BlockLambdaDemo {
    public static void main(String[] args) {
        // Это блочное лямбда выражение, вычисляет
        // факториал целочисленного значения
        NumericFunc factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        };
        System.out.println("Factorial 3 = " + factorial.func(3));
        System.out.println("Factorial 5 = " + factorial.func(5));
    }
}

interface NumericFunc {
    int func(int n);
}
