package com.afoninav.javacore.chapter28;

import java.util.concurrent.Semaphore;

/**
 * Простой пример применения семафора
 *
 */
public class SemDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        new IncThread(sem, "A");
        new DecThread(sem, "B");
    }
}

/**
 * Общий ресурс
 */
class Shared {
    static int count = 0;
}

/**
 * Поток исполнения увеличивающий
 * значение счётчикана еденицу
 */
class IncThread implements Runnable {
    String name;
    Semaphore sem;
    Thread t;

    IncThread(Semaphore sem, String name) {
        this.name = name;
        this.sem = sem;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        try {
            String tName = this.t.getName();
            System.out.println("Запуск потока: " + tName);
            System.out.println("Поток " + tName + " ожидает разрешения.");
            // Получить разрешение
            sem.acquire();
            System.out.println("Поток " + tName + " получил разрешения.");
            for (int i = 0; i < 5; i++) {
                // Получить доступ к общему ресурсу
                Shared.count++;
                System.out.println(tName + ": " + Shared.count);
            }
            // Разрешить если возможно переключение контескста
            Thread.sleep(10);
            System.out.println("Поток " + tName + " освобождает разрешение");
            // Освободить разрешеие
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Поток исполнения уменьшающий
 * значение счётчика на еденицу
 */
class DecThread implements Runnable {
    String name;
    Semaphore sem;
    Thread t;

    DecThread(Semaphore sem, String name) {
        this.name = name;
        this.sem = sem;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        try {
            String tName = this.t.getName();
            System.out.println("Запуск потока: " + tName);
            System.out.println("Поток " + tName + " ожидает разрешения.");
            // Получить разрешение
            sem.acquire();
            System.out.println("Поток " + tName + " получил разрешения.");
            for (int i = 0; i < 5; i++) {
                // Получить доступ к общему ресурсу
                Shared.count--;
                System.out.println(tName + ": " + Shared.count);
            }
            // Разрешить если возможно переключение контескста
            Thread.sleep(10);
            System.out.println("Поток " + tName + " освобождает разрешение");
            // Освободить разрешеие
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}










