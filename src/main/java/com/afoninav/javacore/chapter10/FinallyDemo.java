package com.afoninav.javacore.chapter10;

/**
 * Пример работы оператора finally
 */

public class FinallyDemo {
    // генерация исклюяения в методе
    static void procA(){
        try {
            System.out.println("В теле метода procA().");
            throw new RuntimeException("Demo");
        } finally {
            System.out.println("Блок оператора finally в методе procA()");
        }
    }

    // возврат управления из блока try
    static void procB() {
        try {
            System.out.println("Внури тела метода procB()");
            return;
        } finally {
            System.out.println("Блок оператора finally в методе procB()");
        }
    }

    // выполнить блок try как обычно
    static void procC() {
        try {
            System.out.println("В теле метода procC().");
        } finally {
            System.out.println("Блок оператора finally в методе procC().");
        }
    }

    public static void main(String[] args){
        try {
            procA();
        } catch (Exception e) {
            System.out.println("Исключение перехвачено");
        }
        procB();
        procC();

    }
}













