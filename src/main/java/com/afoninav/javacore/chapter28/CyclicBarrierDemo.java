package com.afoninav.javacore.chapter28;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Пример использзования класса CyclicBarrier.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Запуск потоков.");
        new MyThread1(cb, "A");
        new MyThread1(cb, "B");
        new MyThread1(cb, "C");
    }
}

class MyThread1 implements Runnable {
    CyclicBarrier cb;
    String name;

    MyThread1(CyclicBarrier cb, String name) {
        this.name = name;
        this.cb = cb;
        new Thread(this,this.name).start();
    }

    @Override
    public void run() {
        System.out.println(this.name);
        try {
            this.cb.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}

class BarAction implements Runnable {
    @Override
    public void run() {
        System.out.println("Барьер достигнут.");
    }
}
