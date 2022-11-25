package com.afoninav.javacore.chapter15;

public class LambdaDemo2 {
    public static void main(String[] args) {
        // лямбда-выражение, проверяющее, является-ли число чётным
        NumericTest isEven = (n) -> (n % 2) == 0;
        if (isEven.test(10)) {
            System.out.println("Число чётное");
        } else {
            System.out.println("Нечётное");
        }

        if (isEven.test(9)) {
            System.out.println("Число чётное");
        } else {
            System.out.println("Нечётное");
        }

        // Проверим, не является ли число отрицательным
        NumericTest isNegative = (n) -> n < 0;
        if (isNegative.test(-1)) System.out.println("Отрицательное число");
        if (!isNegative.test(1)) System.out.println("Число не является отрицательным");
    }
}

interface NumericTest {
    boolean test(int n);
}
