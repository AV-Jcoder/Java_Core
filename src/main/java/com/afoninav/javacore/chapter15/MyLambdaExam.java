package com.afoninav.javacore.chapter15;

/**
 * Пример рекурсивных лямбда выражений
 * высчитывающих факториал числа
 * одно статическое
 * другое обчное.
 *
 */

public class MyLambdaExam {

    static Factorial fac = (n) -> n == 1 ? 1 : n * MyLambdaExam.fac.factorial(n-1);
         Factorial facto = (n) -> n == 1 ? 1 : n * this.facto.factorial(n-1);

    public static void main(String[] args) {
        System.out.println(fac.factorial(3));
        System.out.println(new MyLambdaExam().facto.factorial(4));
    }
}

@FunctionalInterface
interface Factorial{
    int factorial(int i);
}