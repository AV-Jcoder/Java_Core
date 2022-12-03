package com.afoninav.javacore.chapter15;

/**
 * Демонстрация применения ссылки на метод экземпляра.
 */

public class MethodRefDemo2 {
    // В этом методе функциональный интерфейс указзывается
    // в качестве первого параметра. Следовательно в качестве параметра может
    // быть передан любой экземпляр этого интерфейса или ссылка на метод.
    static String stringOps(StringFunc4 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] arguments) {
        String inString = "Lambda is very important part of Java language";
        String outString;

        // Создан объект типа MyStringOps2
        MyStringOps2 msop2 = new MyStringOps2();

        // Теперь передаём ссылку на метод экземпляра MyStringOps2 в
        // качестве аргумента методу stringOps()
        outString = stringOps(msop2::strReverse, inString);

        System.out.println("Исходная строка: " + inString);
        System.out.println("Результирующая строка: " + outString);
    }
}

// В этом классе определяется метод для операции со строками
class MyStringOps2 {
     String strReverse(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = str.length()-1; i >=0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}

//Функциональный интерфейс для операции со строками
@FunctionalInterface
interface StringFunc4 {
    String func(String string);
}
