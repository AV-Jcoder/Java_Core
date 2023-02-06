package com.afoninav.javacore.chapter28;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Простой пример выполнения атомарных операций *
 */
public class AtomicDemo {
    public static void main(String[] args) {
        new AtomThread("A");
        new AtomThread("B");
        new AtomThread("C");
    }
}

class Shared1 {
    static AtomicInteger ai = new AtomicInteger(0);
}

// Поток исполнения в котором инкрементируются значения счётчика
class AtomThread implements Runnable {
    String name;

    AtomThread(String name) {
        this.name = name;
        new Thread(this, this.name).start();
    }

    @Override
    public void run() {
        System.out.println("Запуск потока " + this.name);
        for (int i = 0; i < 5; i++) {
            System.out.println("Поток " + this.name + " получено: " + Shared1.ai.getAndSet(i));
        }
    }
}
