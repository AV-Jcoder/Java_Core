package com.afoninav.javacore.chapter28;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Простой пример применения класса RecursiveTask<V> для
 * вычисления суммы значений в массиве типа double.
 */
public class RecursiveTaskDemo {
    public static void main(String[] args) {

        double[] nums = new double[5_000];
        // инициализация массива чередующимися
        // положительными и отрицательными числами
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (i % 2 == 0) ? i : -1*i;
        }

        ForkJoinPool fjp = new ForkJoinPool();
        Sum1 task = new Sum1(nums, 0, nums.length);

        // запуск ForkJoinTask
        double summation = fjp.invoke(task);
        System.out.println("Результат вычислений: " + summation);

    }
}

/**
 * Класс для вычисления суммы значений элементов массива.
 */
class Sum1 extends RecursiveTask<Double> {
    final int seqThresHold = 500;
    double[] data;
    int start, end;

    Sum1(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double result = 0;

        if (end - start < seqThresHold) {
            for (int i = start; i < end; i++) {
                result += data[i];
            }
        } else {
            int middle = (start + end) / 2;
            Sum1 subTask1 = new Sum1(data, start, middle);
            Sum1 subTask2 = new Sum1(data, middle, end);
            subTask1.fork();
            subTask2.fork();
            result = subTask1.join() + subTask2.join();
        }
        return result;
    }
}


