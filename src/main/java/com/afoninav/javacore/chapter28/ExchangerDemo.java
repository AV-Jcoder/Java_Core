package com.afoninav.javacore.chapter28;

import java.util.concurrent.Exchanger;

/**
 * Пример применения класса Exchanger для
 * обмена данными между потоками исполнения.
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        new UserString(exchanger);
        new MakeString(exchanger);
    }
}

// Поток исполнения, формирующий символьную строку
class MakeString implements Runnable {
    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> ex) {
        this.ex = ex;
        this.str = new String();
        new Thread(this).start();
    }

    @Override
    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.str = this.str + ch++;
            }
            // Обмен данными, хранящимися в str, с другим потоком.
            try {
                this.str = ex.exchange(this.str);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// Поток исполнения, использующий символьную строку
class UserString implements Runnable {
    Exchanger<String> ex;
    String str;

    UserString(Exchanger ex) {
        this.ex = ex;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                // Обмен данными с другим потоком.
                this.str = this.ex.exchange("Hello");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Получено " + this.str);
        }
    }
}


















