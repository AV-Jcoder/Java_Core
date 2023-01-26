package com.afoninav.javacore.chapter11;

/**
 * Приостановка, возобновление и остановка потоков исполнения.
 *
 */

public class SuspendResume {
    public static void main(String[] args) {
        NewThread3 ob1 = new NewThread3("Один");
        NewThread3 ob2 = new NewThread3("Два");

        try {
            Thread.sleep(1000);
            ob1.setSuspend();
            System.out.println("Приостановка потока один.");
            Thread.sleep(1000);
            ob1.setResume();
            System.out.println("Возобновление потока один.");

            ob2.setSuspend();
            System.out.println("Приостановка потока два.");
            Thread.sleep(1000);
            ob2.setResume();
            System.out.println("Воззобновление потока два.");

        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван");
        }

        // Ожидание завершения потоков
        System.out.println("Ожидание завершения потоков.");
        try {
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван.");
        }

        System.out.println("Главный поток завершен.");
    }
}

class NewThread3 implements Runnable {
    String name;
    Thread t;
    boolean suspendFlag;

    NewThread3(String name) {
        this.name = name;
        System.out.println("Новый поток " + name);
        suspendFlag = false;
        t = new Thread(this, name);
        t.start();
    }

    @Override // Точка входа в поток исполнения
    public void run() {
        try {
            for (int i = 15; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " прерван.");
        }
        System.out.println(name + " завешен.");
    }

    synchronized void setSuspend() {
        this.suspendFlag = true;
    }

    synchronized void setResume() {
        this.suspendFlag = false;
        notify();
    }
}


