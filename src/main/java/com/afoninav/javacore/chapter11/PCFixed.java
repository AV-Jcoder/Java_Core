package com.afoninav.javacore.chapter11;

/**
 * Правильный пример взаимодействия
 * Producer - Consumer
 * с использованием методов wait(), notify(), notifyAll().
 *
 */

public class PCFixed {
    public static void main(String[] args) {
        Q1 queue = new Q1();
        new Producer1(queue);
        new Consumer1(queue);
        System.out.println("Для остановки нажмите Ctrl-C.");
    }
}

class Q1 {
    int value;
    boolean valueIsSet = false;

    synchronized int get() {
        try {
            while(!valueIsSet) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Получено: " + this.value);
        valueIsSet = false;
        this.notify();
        return this.value;
    }

    synchronized void put(int value) {
        try {
            while (valueIsSet) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
        System.out.println("Отправлено: " + this.value);
        this.valueIsSet = true;
        this.notify();
    }
}

class Producer1 implements Runnable {
    Q1 queue;

    Producer1(Q1 queue) {
        this.queue = queue;
        new Thread(this, "Поставщик").start();
    }

    @Override
    public void run() {
        int value = 0;
        while(true) {
            this.queue.put(value++);
        }
    }
}

class Consumer1 implements Runnable {
    Q1 queue;

    Consumer1(Q1 queue) {
        this.queue = queue;
        new Thread(this, "Потребитель").start();
    }

    @Override
    public void run() {
        while(true) {
            this.queue.get();
        }
    }
}





