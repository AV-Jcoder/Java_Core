package com.afoninav.javacore.chapter11;

/**
 * Неверный(т.е как делать не нужно)
 * пример взаимодействия потоков исполнения типа
 * поставщик - потребитель
 */

public class PC {
    public static void main(String[] args) {
        Q queue = new Q();
        new Producer(queue);
        new Producer(queue);
        new Consumer(queue);

        System.out.println("Нажмите CTRL+C или CTRL+D для остановки.");
    }
}

// Класс синхронизируемой очереди
class Q {
    int n;

    synchronized int get() {
        System.out.println("Получено: " + n);
        return n;
    }

    synchronized void put(int n) {
        this.n = n;
        System.out.println("Отправлено: " + n);
    }
}

// Класс поставщик
class Producer implements Runnable {
    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Поставщик").start();
    }

    @Override
    public void run() {
        int i = 0;
        while(true) {
            q.put(i++);
        }
    }
}

// Класс потребитель
class Consumer implements Runnable {
    Q q;

    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Потребитель").start();
    }

    @Override
    public void run() {
        while (true) {
            q.get();
        }
    }
}





























