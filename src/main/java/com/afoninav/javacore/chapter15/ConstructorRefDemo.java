package com.afoninav.javacore.chapter15;

/**
 * Пример в котором демонстрируется
 * применение ссылки на конструктор класса.
 * ClassName::new
 */

public class ConstructorRefDemo {
    public static void main(String[] args) {
        // Создадим переменную mf типа MyFunc3, в которою
        // передадим ссылку на конструктор класса MyClass2.
        // Ссылка MyClass2::new обращается к параметремезированному
        // конструктору, т.к. метод func() принимает один параметр типа int.
        MyFunc3 mf = MyClass2::new;
        MyClass2 mc21 = mf.func(88);
        System.out.println("Значение val объекта mc21 равно: " + mc21.getVal());
    }
}

class MyClass2 {
    private int val;

    MyClass2() {
    }

    MyClass2(int val) {
        this.val = val;
    }

    int getVal() {
        return this.val;
    }
}

// Метод этого интерфейса возвращает объект типа MyClass.
@FunctionalInterface
interface MyFunc3 {
    MyClass2 func(int i);
}
