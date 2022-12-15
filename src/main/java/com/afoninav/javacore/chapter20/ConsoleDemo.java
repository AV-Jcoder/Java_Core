package com.afoninav.javacore.chapter20;

import java.io.Console;

/**
 * Пример использования класса Console.
 */

public class ConsoleDemo {
    public static void main(String[] args) {

        String str;
        Console con;

        con = System.console();

        if (con == null) return;

        str = con.readLine("Введите строку: ");
        con.printf("Введёная вами строка: %s \n", str);
    }
}
