package com.afoninav.javacore.chapter15;

/**
 * Пример использования ссылки на конструктор массива.
 *
 * type[]::new
 *
 */

public class ArrayConstructorRefDemo {
    public static void main(String[] args) {
        // Присваваем ссылку на конструктор массива в переменную функционального интерфейса
        MyArrayCreator<MyClass1[]> myArrayConstructor = MyClass1[]::new;

        // Объявляем и инициализируем массив
        MyClass1[] mc1Array = myArrayConstructor.func(3);

        // По умолчанию все объекты массива проинициализзированы как null
        // поэтому присваиваем им новые значения
        mc1Array[0] = new MyClass1(11);
        mc1Array[1] = new MyClass1(22);
        mc1Array[2] = new MyClass1(33);

        // Выводим на экран значения
        for (MyClass1 mc1:
             mc1Array) {
            System.out.println(mc1.getVal());
        }
    }
}

class MyClass1 {
    private int val;

    MyClass1() {}

    MyClass1(int val) {
        this.val = val;
    }

    int getVal() {
        return this.val;
    }
}

@FunctionalInterface
interface MyArrayCreator<T> {
    T func(int val);
}
