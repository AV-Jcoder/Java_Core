package com.afoninav.javacore.chapter11;

/**
 * Создание трёх дочерних потоков исполнения.
 *
 */

public class MultiThreadDemo {
    public static void main(String[] args) {
        new ChildThread("One");
        new ChildThread("Two");
        new ChildThread("Three");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s interrupted: %s\n", Thread.currentThread().getName(), e);
        }
        System.out.printf("Thread %s is finished.\n", Thread.currentThread().getName());
    }
}

class ChildThread implements Runnable {

    String name;
    Thread thread;

    // Создать и запустить новый поток на исполнение
    ChildThread(String name) {
        this.name = name;
        this.thread = new Thread(this, this.name);
        System.out.println("Creating new Thread: " + thread);
        thread.start();
    }

    // Определить точку входа в поток исполнения
    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ", " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.printf("Thread %s interrupted: %s.\n", this.name, e);
        }
        System.out.printf("Thread %s finished.\n", this.name);
    }
}

