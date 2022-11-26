package com.afoninav.javacore.chapter15;

public class GenericFunctionalInterfaceDemo {
    public static void main(String[] args) {
        // Использование строки в качестве
        // параметра функционального интерфейса
        SomeFunc<String> reverse = (str) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = str.length()-1; i >=0; i--) {
                sb.append(str.charAt(i));
            }
            return sb.toString();
        };
        String s1 = new String("Привет Мир!");
        System.out.println("Результат работы лямбда-выражения:");
        System.out.printf("Строка до: %s \nСтрока после: %s \n",s1,reverse.func(s1));

        // Целочисленный вариант параметра функционального интерфейса
        SomeFunc<Integer> factorial = (num) -> {
            int result = 1;
            for (int i = 1; i <= num; i++) {
                result *= i;
            }
            return result;
        };
        int num = 5;
        System.out.println("\nРезультат работы лямбда-выражения:");
        System.out.printf("Факториал числа %d равен %d \n",num,factorial.func(num));
    }
}

@FunctionalInterface
interface SomeFunc<T> {
    T func(T t);
}