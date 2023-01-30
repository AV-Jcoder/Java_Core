package com.afoninav.javacore.chapter28;

import java.util.concurrent.CountDownLatch;

/**
 * Пример применения объекта класса CountDownLatch
 *
 */

public class CDLDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);
        System.out.println("Запуск потока исполнения");
        new MyThread(cdl);
        try {
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Завершение потока исполнения");
    }
}

class MyThread implements Runnable {
    CountDownLatch cdl;

    MyThread(CountDownLatch cdl) {
        this.cdl = cdl;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            this.cdl.countDown();
        }
    }
}
