package com.afoninav.javacore.chapter15;

/**
 * В примере реализована простая фабрика,
 * используя ссылку на конструктор.
 *
 */

public class ConstructorRefDemo3 {
    // Фабричный метод для объектов разных классов.
    // У каждого класса должен быть свой конструктор,
    // принимающий один параметр типа Т. Параметр R
    // обозначает тип создаваемого объекта.
    static <R,T> R myClassFactory(MyFunc5<R,T> constr, T value) {
        return constr.func(value);
    }

    public static void main(String[] args) {
        // Создать ссылку на конструктор класса MyClass5.
        // В данном случае оператор new обращается к конструктору,
        // принимающему один аргумент.
        MyFunc5<MyClass5<Double>,Double> myClassConstructor = MyClass5<Double>::new;

        // Создаём экземпляр класса MyClass5, используя фабричный метод
        MyClass5<Double> myClass5 = myClassFactory(myClassConstructor,5.5);
        System.out.println(myClass5.getVal());


        // Присвоим ссылку на конструктор в переменныую функционального интерфейса.
        MyFunc5<MyClass4,String> myClassConstructor2 = MyClass4::new;

        // Создадим экземпляр класса MyClass4, используя метод myClassFactory()
        MyClass4 myClass4 = myClassFactory(myClassConstructor2,"Hello");
        System.out.println(myClass4.getVal());

    }

}

// Простой необощенный класс
class MyClass5<T> {
    private T val;

    MyClass5() {}

    MyClass5(T val) {
        this.val = val;
    }

    T getVal() {
        return this.val;
    }
}

// Простой необощенный класс
class MyClass4 {
    String str;

    MyClass4() {}

    MyClass4(String str) {
        this.str = str;
    }

    String getVal() {
        return this.str;
    }
}

@FunctionalInterface
interface MyFunc5<R,T> {
    R func(T val);
}


