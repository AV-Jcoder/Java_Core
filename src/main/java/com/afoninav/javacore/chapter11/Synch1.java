package com.afoninav.javacore.chapter11;

/**
 * Пример синхронизации вызовов метода, не объявленнго как synchronized.
 *
 * По факту мы делаем для конкретного экземпляра
 * обёртку для вызова его несинхронизированного метода.
 *
 * И если обращаться через обёртку в вызову метода этого экземпляра то
 * доступ будет последовательным.
 * А если кто-то получит ссылку на данный экземпляр то будет использовать
 * его вызов методов напрямую.
 * Следовательно объект должен быть инкапсулирован.
 *
 */

public class Synch1 {
    public static void main(String[] args) {
        CallMe2 target = new CallMe2();
        Caller2 caller2 = new Caller2("Welcome", target);
        Caller2 caller3 = new Caller2("to synchronized", target);
        Caller2 caller4 = new Caller2("world!", target);
    }

}

class Caller2 implements Runnable{
    CallMe2 target;
    String msg;
    Thread thread;

    Caller2(String msg, CallMe2 target) {
        this.msg = msg;
        this.target = target;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        synchronized(target) {
            this.target.call(msg);
        }
    }
}

class CallMe2 {
    void call(String msg) {
        try {
            System.out.print("[" + msg);
            Thread.sleep(1000);
            System.out.println("]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}