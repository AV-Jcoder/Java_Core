package com.afoninav.javacore.chapter28;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Простой пример программы, позволяющей эксперементировать с
 * эффектом от изменения порогового значения и уровня параллелизма
 * при выполнении задач ForkJoinTask.
 */
public class ForkJoinExperiment {
    public static void main(String[] args) {
        int pLevel;
        int threshold;

        if (args.length != 2) {
            System.out.println("Для запуска программы введите в консоли:");
            System.out.println("java ForkJoinExperiment 4 10000");
        }

        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        // переменные для измерения времени выполнения задачи
        long beginT, endT;

        // создать пул задач.
        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        double[] nums = new double[1_000_000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }

        Transform task = new Transform(nums, 0, nums.length, threshold);

        beginT = System.currentTimeMillis();
        fjp.invoke(task);
        endT = System.currentTimeMillis();
        System.out.println("Уровень параллелизма: " + pLevel);
        System.out.println("Порог последовательной обработки: " + threshold);
        System.out.println("Время, затраченное на выполнение: " + (endT-beginT) + " mc\n");
    }
}

class Transform extends RecursiveAction {
    // Порог последоватлеьного выполнения
    int seqThreshold;
    double[] array;
    int start, end;

    Transform(double[] array, int start, int end, int seqThreshold) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.seqThreshold = seqThreshold;
    }

    @Override
    protected void compute() {
        // если колчиество элементов меньше порогового значения
        // выполнить обработку последовательно.
        if (end - start < seqThreshold) {
            for (int i = start; i < end; i++) {
                if (array[i] % 2 == 0) {
                    array[i] = Math.sqrt(array[i]);
                } else {
                    array[i] = Math.cbrt(array[i]);
                }
            }
        } else {
            // иначе продолжать разделение данных
            // на меньшие части.
            int middle = (start+end)/2;
            invokeAll(
                    new Transform(array, start, middle, seqThreshold),
                    new Transform(array, middle, end, seqThreshold)
            );
        }
    }
}










