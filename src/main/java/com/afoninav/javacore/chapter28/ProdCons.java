package com.afoninav.javacore.chapter28;

import java.util.concurrent.Semaphore;

/**
 * Реализзация поставщика и потребителя, используя
 * семафоры для управления синхронизацией.
 *
 * Методы
 * acquire() - заполучить доступ к объекту.
 * release() - отдать доступ к объекту.
 *
 */

public class ProdCons {
    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}

class Q {
    int n;

    // Начать с недоступного семафора потребителя
    // разврешение на считывание даётся в методе put()
    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);

    int get() {
        try {
            this.semCon.acquire(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Получено: " + this.n);
        semProd.release(1);
        return this.n;
    }

    void put(int n) {
        try {
            this.semProd.acquire(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Отправлено: " + n);
        this.n = n;
        this.semCon.release(1);
    }
}

class Producer implements Runnable {
    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.q.put(i);
        }
    }
}

class Consumer implements Runnable {
    Q q;

    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.q.get();
        }
    }
}
