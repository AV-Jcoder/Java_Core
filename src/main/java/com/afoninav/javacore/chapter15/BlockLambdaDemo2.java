package com.afoninav.javacore.chapter15;

public class BlockLambdaDemo2 {
    public static void main(String[] args) {
        // Это лямбда выражение меняет порядок следования
        // символов в строке на обратный
        StringFunc reverse = (str) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = str.length()-1; i >=0; i--) {
                sb.append(str.charAt(i));
            }
            return sb.toString();
        };
        String s1 = "Привет Мир!";
        System.out.println("Пример работы функции:");
        System.out.printf("До: %s \n", s1);
        System.out.printf("После: %s \n",reverse.func(s1));
    }
}

interface StringFunc {
    String func(String arg);
}
