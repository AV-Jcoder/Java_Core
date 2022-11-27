package com.afoninav.javacore.chapter15;

/**
 * Лямбда-выражения и исключения
 *
 */

public class LambdaExceptionDemo {
    public static void main(String[] args) throws EmptyArrayException {
        double[] values = {1.0, 2.0, 3.0, 4.0};

        // В этом лямбда выражении вычисляется среднее величина
        // для всех зачений массива типа double

        DoubleNumericArrayFunc average = (n) -> {
            double sum = 0;
            if (n.length == 0) {
                throw new EmptyArrayException();
            }
            for (int i = 0; i < n.length; i++) {
                sum = sum + n[i];
            }
            return sum / n.length;
        };

        System.out.println("Средняя величина значений массива равна: " + average.func(values));

        // Строка кода ниже приводит к генерации исключения
        System.out.println("После вызова метода из переменной average, " +
                "будет сгенерировано исключение:" + average.func(new double[0]));
    }
}

@FunctionalInterface
interface DoubleNumericArrayFunc {
    double func (double[] n) throws EmptyArrayException;
}

class EmptyArrayException extends Exception {
    EmptyArrayException() {
        super("Массив пуст");
    }
}