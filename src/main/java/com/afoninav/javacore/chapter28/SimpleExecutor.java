package com.afoninav.javacore.chapter28;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Простой пример исполнителя
 */
public class SimpleExecutor {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdl3 = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);
        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("Запуск потоков.");

        es.execute(new MyThread4(cdl, "A"));
        es.execute(new MyThread4(cdl2, "B"));
        es.execute(new MyThread4(cdl3, "C"));
        es.execute(new MyThread4(cdl4, "D"));
        es.execute(new MyThread4(cdl4, "E"));

        try {
            cdl.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        es.shutdown();
        System.out.println("Завершение работы потоков.");
    }
}

class MyThread4 implements Runnable {
    String name;
    CountDownLatch cdl;

    MyThread4(CountDownLatch cld, String name) {
        this.name = name;
        this.cdl = cld;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(300);
                System.out.println(this.name + ": " + i);
                this.cdl.countDown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
