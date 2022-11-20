package com.afoninav.javacore.chapter10;

/**
 * В этой программе создаётся
 * специальный тип исключения
 */
public class MyException extends Exception {
    private int detail;

    MyException(int a) {
        detail = a;
    }
    @Override
    public String toString() {
        return "MyException[" + detail + "]";
    }
}
