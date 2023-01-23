package com.afoninav.javacore.chapter11;

/**
 * Пример получения ссылки на главный поток исполнения
 */

public class CurrentThreadDemo {
    public static void main(String[] args) {

        // Получить экземпляр главного потока исполнения
        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread);

        // Изменить имя потока исполнения
        mainThread.setName("Main Thread");
        System.out.println(mainThread);

        // Вывести на консоль 5 раз название главной нити с задержкой 1 сек.
        for (int i = 5; i >= 0; i--) {
            try {
                Thread.sleep(1000);
                if (i == 0)
                    System.out.println("Поехали!");
                else
                    System.out.println(i);
            } catch (InterruptedException e) {
                System.out.println("Поток исполнения прерван: " + e);
            }
        }
    }
}
