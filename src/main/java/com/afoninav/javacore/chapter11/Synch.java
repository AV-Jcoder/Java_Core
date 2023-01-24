package com.afoninav.javacore.chapter11;

/**
 * Пример не синхронизированной программы, где метод call() у объекта CallMe
 * вызывается одновременно различными потоками.
 *
 */

public class Synch {
    public static void main(String[] args) {
        CallMe target = new CallMe();
        Caller ob1 = new Caller(target, "Добро пожаловать");
        Caller ob2 = new Caller(target, "в синхронизированный");
        Caller ob3 = new Caller(target, "мир!");

        // Ожидание завершения потока исполнения
        try {
            ob1.thread.join();
            ob2.thread.join();
            ob3.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Прервано");
        }
    }
}

class CallMe {

    // Этот метод мог быть обявлен как synchronized для
    // последовательного использования различными потоками
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Поток прерван: " + e);
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg;
    CallMe target;
    Thread thread;

    Caller(CallMe target, String msg) {
        this.msg = msg;
        this.target = target;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        this.target.call(msg);
    }
}
