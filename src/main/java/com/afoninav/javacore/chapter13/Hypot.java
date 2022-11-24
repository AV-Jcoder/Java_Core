package com.afoninav.javacore.chapter13;

import static java.lang.Math.*;
import static java.lang.System.out;

/**
 * Пример класса со статическим импортом
 *
 */

public class Hypot {
    public static void main(String[] args) {
        double c = 0;
        double a = 5;
        double b = 3;

//        c = Math.pow(a,2) + Math.pow(b,2);
//        c = Math.sqrt(c);

        // можно обращаться ко всем статик членам класса Math
        c = pow(a,2) + pow(b,2);
        c = sqrt(c);

        // импортирована статическая переменная out класса System
        out.println("Гипотенуза = " + c);
    }
}
