package com.afoninav.javacore.chapter11;

/**
 * Пример демонстрирующий работу метода
 * join() - ожидать завершения потоков исполнения
 * и метода isAlive()
 *
 */

public class DemoJoin {
    public static void main(String[] args) {

        // создаём и ззапускаем потоки
        NewThread2 ob1 = new NewThread2("Один");
        NewThread2 ob2 = new NewThread2("Два");
        NewThread2 ob3 = new NewThread2("Три");

        // проверяем потоки
        System.out.println("Main: Поток Один запущен: " + ob1.thread.isAlive());
        System.out.println("Main: Поток Два запущен: " + ob2.thread.isAlive());
        System.out.println("Main: Поток Три запущен: " + ob3.thread.isAlive());

        // ожидание завершения потоков исполнения
        try {
            System.out.println("Main: Ожидание завершения потоков.");
            ob1.thread.join();
            ob2.thread.join();
            ob3.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main: Главный поток прерван.");
        }

        // проверяем потоки
        System.out.println("Main: Поток Один запущен: " + ob1.thread.isAlive());
        System.out.println("Main: Поток Два запущен: " + ob2.thread.isAlive());
        System.out.println("Main: Поток Три запущен: " + ob3.thread.isAlive());
        System.out.println("Main: Главный поток завершен.");
    }
}

class NewThread2 implements Runnable {

    String name;
    Thread thread;

    NewThread2(String name) {
        this.name = name;
        this.thread = new Thread(this, this.name);
        System.out.println("Новая нить: " + thread);
        thread.start();
    }

    // Точка входа в поток исполнения
    @Override
    public void run() {
        try {
            for (int i = 5; i >= 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + ": прерван.");
        }
        System.out.println(name + ": завершен.");
    }
}
