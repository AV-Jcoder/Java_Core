package com.afoninav.javacore.chapter28;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Простой пример реализзации стратегии "разделяй и влавствуй".
 * В данном примере применяется класс RecursiveAction.
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        // создать пул задач
        ForkJoinPool fjp = new ForkJoinPool();

        double[] nums = new double[100_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }

        System.out.println("Часть исходной поседовательности:");
        for (int i = 0; i < 10; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println("\n");

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        // Запустить главную задачу на выполнение.
        fjp.invoke(task);

        System.out.println("Часть преобразованной последовательности:");
        for (int i = 0; i < 10; i++) {
            System.out.format("%.4f ",nums[i]);
        }
        System.out.println();


    }
}

/**
 * Класс преобразует элементы массива в их квадратные корни.
 */
class SqrtTransform extends RecursiveAction {
    // В данном примере пороговое значение устанавливается равным 1000.
    final int seqThreshold = 1000;

    // обрабатываемый массив
    double[] data;

    // определить часть обрабатываемых данных
    int start, end;

    SqrtTransform(double[] vals, int s, int e) {
        this.data = vals;
        this.start = s;
        this.end = e;
    }

    // Параллельное вычисление
    @Override
    public void compute() {
        // Если количество элементров меньше порогового значения,
        // выполнить дальнейшую обработку последовательно.
        if (end - start < seqThreshold) {
            // Преобразовать значение каждого элемента массива
            // в его квадратный корень.
            for (int i = start; i < end ; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            // Иначе продолжить разделение данных на
            // меньшие части

            // найти середину
            int middle = (start + end) / 2;

            // запустить новые подзадачи на выполнение, используя
            // разделенные на части данные.
            invokeAll(new SqrtTransform(data, start, middle),
                        new SqrtTransform(data, middle,end));
        }
    }
}












