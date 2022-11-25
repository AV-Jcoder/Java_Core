package com.afoninav.javacore.chapter15;

public class LambdaDemo {
    public static void main(String[] args) {
        // объявляем ссылку на функциональный интерфейс
        MyNumber myNum;

        // Ниже лямбда-выражение является константным выражением.
        // Когда оно присваивается ссылочной переменной myNum, получается
        // экземпляр класса, в котором лямбда выражение реализует
        // метод getValue() из функционального интерфейса MyNumber
        myNum = () -> 123.45;
        System.out.println(myNum.getClass().toString());

        // мы можем вызвать метод getValue(), который определён в функциональном интерфейсе
        // и реализован через лямбда выражение и присвоен объекту, хранящимся в myNum
        System.out.println("Фиксированное значение : " + myNum.getValue());

        // А здесь используется более сложное выражение
        myNum = () -> Math.random()*100;

        // В следующих строках кода вызывается лямбда выражение
        // реализованое в 19-ой строке кода.
        System.out.println("Случайное значение : " + myNum.getValue());
        System.out.println("Еще одно случайное значение : " + myNum.getValue());

        // лямбда выражение должно быть совместимо с абстрактным методом,
        // определяемом в функциональном интерфейсе.
        // Должны совпадать параметры по типу и количеству.
        // Поэтому строка ниже выдаст ошибку компиляции.
//        myNum = () -> "123.45";
    }
}

@FunctionalInterface
interface MyNumber {
    double getValue();
}
