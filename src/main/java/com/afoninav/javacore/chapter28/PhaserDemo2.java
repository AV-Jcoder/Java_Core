package com.afoninav.javacore.chapter28;

import java.util.concurrent.Phaser;

/**
 * Пример с расширением класса Phaser,
 * чтобы переопределить метод onAdvance()
 * таким образом, чтобы было выполнено
 * определённое количество фаз.
 */

public class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser(1,4);
        System.out.println("Запуск потоков\n");
        new MyThread3(phaser,"A");
        new MyThread3(phaser,"B");
        new MyThread3(phaser,"C");

        // ожидать завершения определённого количесва фаз
        while(!phaser.isTerminated()) {
            phaser.arriveAndAwaitAdvance();
        }

        System.out.println("Синхронизатор фаз завершен.");
    }
}

class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int phaseCount) {
        super(parties);
        this.numPhases = phaseCount - 1;
    }

    // переопределить метод onAdvance() чтобы выполнить
    // определённое количество фаз
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        System.out.println("Фаза " + phase + " завершена.\n");
        // true - если все фазы завершены, иначе false.
        return phase == this.numPhases || registeredParties == 0;
    }
}

class MyThread3 implements Runnable {
    Phaser phaser;
    String name;

    public MyThread3(Phaser phaser, String name) {
        this.phaser = phaser;
        this.phaser.register();
        this.name = name;
        new Thread(this, this.name).start();
    }

    @Override
    public void run() {
        while(!this.phaser.isTerminated()) {
            System.out.println("Поток " + this.name + " начинает фазу " + this.phaser.getPhase());
            this.phaser.arriveAndAwaitAdvance();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}