package com.afoninav.javacore.chapter28;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        new ReadThread("A");
        new ReadThread("B");
        new ReadThread("C");
        new WriteThread("Z");
    }
}

class SharedRes {
    private static int value;
    private final static ReadWriteLock rwLock;
    private final static Lock readLock;
    private final static Lock writeLock;

    static {
        value = 0;
        rwLock = new ReentrantReadWriteLock();
        readLock = rwLock.readLock();
        writeLock = rwLock.writeLock();
    }

    static void setValue(int value) {
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " блокирует ресурс для записи.");
        SharedRes.value = value;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + ": " + SharedRes.value);
        System.out.println(Thread.currentThread().getName() + " снял блокировку.");
        writeLock.unlock();
    }

    static int getValue() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " блокирует ресурс для чтения.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": " + SharedRes.value);
            return value;
        } finally {
            System.out.println(Thread.currentThread().getName() + " снял блокировку.");
            readLock.unlock();
        }
    }
}

class ReadThread implements Runnable {
    String name;

    ReadThread(String name) {
        this.name = name;
        new Thread(this, this.name).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int value = SharedRes.getValue();
        }
    }
}


class WriteThread implements Runnable {
    String name;

    WriteThread(String name) {
        this.name = name;
        new Thread(this, this.name).start();
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SharedRes.setValue(i);
        }
    }
}
