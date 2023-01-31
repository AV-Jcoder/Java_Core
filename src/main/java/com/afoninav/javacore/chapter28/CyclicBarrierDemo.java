package com.afoninav.javacore.chapter28;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Пример использзования класса CyclicBarrier.
 *
 * В этом примере поток А не может завершится
 * раньше, чем стартанёт поток С.
 * Это гарантирует метод await().
 *
 * Когда число элементов в барьере достигнет 3,
 * то потоки высвобождаются, и запускается еще поток типа BarAction.
 *
 * После этого объект CyclicBarrier снова попытается остановить 3 потока
 * и действия будут повторяться.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Запуск потоков.");
        new MyThread1(cb, "A");
        new MyThread1(cb, "B");
        new MyThread1(cb, "C");
        new MyThread1(cb, "X");
        new MyThread1(cb, "Y");
        new MyThread1(cb, "Z");
    }
}

class MyThread1 implements Runnable {
    CyclicBarrier cb;
    String name;

    MyThread1(CyclicBarrier cb, String name) {
        this.name = name;
        this.cb = cb;
        new Thread(this,this.name).start();
    }

    @Override
    public void run() {
        System.out.println(this.name + " запущен.");
        try {
            this.cb.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.name + " завершен.");
    }
}

class BarAction implements Runnable {
    @Override
    public void run() {
        System.out.println("Барьер достигнут.");
    }
}
