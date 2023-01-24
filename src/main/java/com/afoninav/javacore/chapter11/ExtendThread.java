package com.afoninav.javacore.chapter11;

/**
 * Пример создания второго потока с помощью
 * расширения класса Thread
 *
 */

public class ExtendThread {
    public static void main(String[] args) {
        new MyThread();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ", " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main thread finished");
    }
}

class MyThread extends Thread {

    // Создать и запустить новый поток с помощью конструктора
    MyThread() {
        super("Demonstration thread");
        System.out.println("Child thread: " + this);
        this.start();
    }

    // Точка входа во второй поток исполнения
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ", " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

