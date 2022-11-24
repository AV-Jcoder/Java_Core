package com.afoninav.javacore.chapter13;

/**
 * Пример с перегрузкой конструкторов
 *
 */

public class MyClass {
    int a;
    int b;

    MyClass() {
        this(0);
    }

    MyClass(int a) {
        this(a,a);
    }

    MyClass(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        MyClass obj1 = new MyClass();
        System.out.printf("a = %d, b = %d.\n",obj1.a,obj1.b);

        MyClass obj2= new MyClass(6);
        System.out.printf("a = %d, b = %d.\n",obj2.a,obj2.b);

        MyClass obj3= new MyClass(8,9);
        System.out.printf("a = %d, b = %d.\n",obj3.a,obj3.b);
    }
}
