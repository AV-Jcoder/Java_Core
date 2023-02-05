package com.afoninav.javacore.chapter28;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Простой пример блокировки
 */
public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new LockThread(lock, "A");
        new LockThread(lock, "B");
    }
}

/**
 * Общий ресурс
 */
class Shared2 {
    static int count;
}

/**
 * Реализация потока
 */
class LockThread implements Runnable {
    String name;
    Lock lock;

    public LockThread(Lock lock, String name) {
        this.name = name;
        this.lock = lock;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Запуск потока " + this.name);
            System.out.println("Поток " + this.name + " ожидает блокировки счётчика.");
            // Блокировать доступ (тут просто блокируется поток, а не ресурс Shared2)
            this.lock.lock();
            System.out.println("Поток " + this.name + " блокирует счётчик.");
            Shared2.count++;
            System.out.println("Поток " + this.name + ": " + Shared2.count);
            // Переключение контекста если это возможно
            System.out.println("Поток " + this.name + " ожидает.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Снять блокировку
            System.out.println("Поток " + this.name + " разблокирует счётчик.");
            this.lock.unlock();
        }
    }
}
