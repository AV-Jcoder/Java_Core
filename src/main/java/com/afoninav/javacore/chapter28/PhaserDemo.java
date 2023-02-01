package com.afoninav.javacore.chapter28;

import java.util.concurrent.Phaser;

/**
 * Пример применения класса Phaser.
 *
 */
public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int curPhase;
        System.out.println("Threads start");
        new MyThread2(phaser, "A");
        new MyThread2(phaser, "B");
        new MyThread2(phaser, "C");

        // Ожидание завершения всеми потоками исполнения первой фазы
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + curPhase + " завершена.");

        // Ожидание завершения всеми потоками исполнения второй фазы
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + curPhase + " завершена.");

        // Ожидание завершения всеми потоками исполнения третей фазы
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + curPhase + " завершена.");

        // Снять основной поток исполнения с регистрации
        phaser.arriveAndDeregister();

        if (phaser.isTerminated()) {
            System.out.println("Синхронизатор фаз завершен.");
        }
    }
}

// Поток исполнения использзующий синхрониззатор фаз
class MyThread2 implements Runnable {
    Phaser phaser;
    String name;

    MyThread2(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        this.phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Поток исполнения " + this.name + " начинает первую фазу");
        this.phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Поток исполнения " + this.name + " начинает вторую фазу");
        this.phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Поток исполнения " + this.name + " начинает третью фазу");
        this.phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.phaser.arriveAndDeregister();
    }
}
























