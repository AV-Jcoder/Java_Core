package com.afoninav.javacore.chapter11;

/**
 * Пример создания второго потока исполнения с помощью
 * реализации интерфейса Runnable.
 *
 */

public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new NewThread(), "Мой новый поток");
        thread.start();

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Главный поток:" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван: " + e);
        }
        System.out.println("Главный поток завершен");
    }
}

class NewThread implements Runnable {
    @Override
    public void run(){
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Дочерний поток: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Дочерний поток прерван: " + e);
        }
        System.out.println("Дочерний поток завершен.");
    }
}
