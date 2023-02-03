package com.afoninav.javacore.chapter28;

import java.util.concurrent.*;

/**
 * Пример использования интерфэйсов Callable и Future.
 */
public class CallableDemo {
    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(3);

        Future<Integer> f1;
        Future<Double> f2;
        Future<Integer> f3;

        System.out.println("Запуск потоков:");

        f1 = es.submit(new Sum(5));
        f2 = es.submit(new Hypot(4,3));
        f3 = es.submit(new Factorial(4));

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        es.shutdown();
        System.out.println("Завершено.");
    }
}

/**
 * Три потока исполнения для вычислений:
 *
 * Sum - считает сумму ряда чисел.
 */
class Sum implements Callable<Integer> {
    int maxNumOfRow;

    Sum(int maxNumOfRow) {
        this.maxNumOfRow = maxNumOfRow;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 0; i <= maxNumOfRow; i++) {
            result += i;
        }
        return result;
    }
}

/**
 * Hypot - находит гипотенузу прямоугольного треугольника.
 */
class Hypot implements Callable<Double> {
    int a;
    int b;

    Hypot(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Double call() throws Exception {
        double result = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
        return result;
    }
}

/**
 * Factorial - вычисляет факториал ззаданногог числа.
 */
class Factorial implements Callable<Integer> {
    int number;

    Factorial(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}
