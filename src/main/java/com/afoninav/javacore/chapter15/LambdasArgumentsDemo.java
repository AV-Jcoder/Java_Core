package com.afoninav.javacore.chapter15;

import java.util.Locale;

/**
 * Лямбда-выражения в качестве аргументов метода.
 *
 */

public class LambdasArgumentsDemo {

    // Первый параметр этого метода имеет тип функционального интерфейса.
    // Следовательно ему можно передать ссылку на любой экземпляр этого интерфейса,
    // включая экземпляр, создаваемый в лямбда выражении.
    // Второй параметр обозначает обрабатываемую строку.
    public static String stringOP(StringFunc2 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {

        String inString = "Hello World!";
        String outString;
        System.out.println("Строка до: " + inString);

        // Преобразование в верхний регистр всех символов
        // методу stringOP в качестве первого аргумента передаётся лямбда-выражение,
        // создающее экземпляр интерфейса StringFunc2.
        // в качестве второго аргумента передаётся строка.
        outString = stringOP((s) -> s.toUpperCase(Locale.ROOT), inString);
        System.out.println("Строка после: " + outString);

        // Блочное лямбда выражение, удаляющее пробелы из сроки
        outString = stringOP((s) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    continue;
                }
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }, inString);
        System.out.println("Строка после удаления пробелов: " + outString);

        // Передача в метод, в качестве параметра экземпляр интерфейса StringFunc2
        // созданный заранее в лямбда выражении.
        StringFunc2 reverse = (string) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = string.length()-1; i >=0; i--) {
                sb.append(string.charAt(i));
            }
            return sb.toString();
        };

        // Теперь передаём экземпляр StringFunc2, на который указывает переменная reverse,
        // передаём в метод функцтонального интерфейса.
        outString = stringOP(reverse, inString);
        System.out.println("Строка после reverse: " + outString);
    }
}

@FunctionalInterface
interface StringFunc2 {
    String func(String str);
}
