package com.afoninav.javacore.chapter15;

/**
 * Пример применения ссылки на конструктор обобщенного класса.
 *
 * На практике такое не используется, т.к. наличие двух обозначений
 * одного и тогоже конструктора может привести к конфликтным ситуациям.
 *
 */

public class ConstructorRefDemo2 {
    public static void main(String[] args) {

        // Создаём ссылку на конструктор обобщенного класса MyClass<T>
        MyFunc4<Integer> myClassConst = MyClass3<Integer>::new;

        // Создаём экземпляр класса MyClass.
        MyClass3<Integer> mc3 = myClassConst.func(5);

        System.out.println("Значение val в объекте mc3 равно: " + mc3.getVar());
    }
}

class MyClass3<T> {
    private T var;

    MyClass3(){}

    MyClass3(T var){
        this.var = var;
    }

    T getVar() {
        return this.var;
    }
}

// Функциональный интерфейс тоже обобщен
interface MyFunc4<T> {
    MyClass3<T> func(T var);
}