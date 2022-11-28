package com.afoninav.javacore.chapter15;

/**
 * Демонстрация применения ссылки на статический метод.
 *
 */

public class MethodRefDemo {
    // в этом методе функциональный интерфейс указывается в качестве
    // типа первого его параметра. Следовательно методу может быть передан
    // любой экземпляр этого интерфейса, включая и ссылку на метод
    static String stringOP(StringFunc3 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "Лямбда-выражения повышают эффективность Java";
        String outStr;

        // здесь ссылка на метод strReverse() передаётся методу stringOP()
        outStr = stringOP(MyStringOps::strReverse, inStr);
        System.out.println("Исходная строка: " + inStr);
        System.out.println("Обращёная строка: " + outStr);

    }
}

// в этом классе определяется статический метод strReverse()
class MyStringOps {
    static String strReverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length()-1; i >=0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}

// Функциональный интерфейс для операции с симовлольными строками
@FunctionalInterface
interface StringFunc3 {
    String func(String s);
}

