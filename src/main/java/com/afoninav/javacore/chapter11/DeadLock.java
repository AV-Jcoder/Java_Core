package com.afoninav.javacore.chapter11;

/**
 * Пример взаимной блокировки потоков
 *
 */

public class DeadLock implements Runnable {

    A a = new A();
    B b = new B();

    DeadLock() {
        Thread.currentThread().setName("Главный поток");
        Thread thread = new Thread(this, "Соперничащий поток");
        thread.start();
        a.foo(b);   // получить блокировку для объекта a
                    // в этом потоке исполнения.
    }

    @Override
    public void run() {
        b.bar(a);   // получить блокировку для объекта b
                    // в другом потоке исполнения.
        System.out.println("Назад в другой поток.");
    }

    public static void main(String[] args) {
        new DeadLock();
    }
}

class A {

    synchronized void foo(B b) {
        // Получить информацию о вызывающем потоке
        String name = Thread.currentThread().getName();
        System.out.println(name + " внутри A.foo()");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " пытается вызвать метод B.last()");
        b.last();
    }

    synchronized void last() {
        System.out.println("В методе A.last()");
    }
}

class B {

    synchronized void bar(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " внутри B.bar()");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " пытается вызвать метод A.last()");
        a.last();
    }

    synchronized void last() {
        System.out.println("В методе B.last()");
    }
}