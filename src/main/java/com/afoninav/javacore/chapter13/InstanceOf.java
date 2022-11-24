package com.afoninav.javacore.chapter13;

public class InstanceOf {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();
        if (a instanceof A) {
            System.out.println("a является экземпляром А");
        }
        if (b instanceof B) {
            System.out.println("b является экземпляром B");
        }
        if (c instanceof C) {
            System.out.println("c является экземпляром С");
        }
        if (c instanceof A) {
            System.out.println("c можно привести к классу А");
        }
        if (a instanceof C) {
            System.out.println("a можно привести к типу С");
        }
        System.out.println();

        // сравнение с порождёнными типами
        A obj;
        obj = d;
        System.out.println("obj ссылается на объект в куче типа D");
        if (obj instanceof D) {
            System.out.println("obj является экземпляром D");
        }
        System.out.println();

        obj = c;
        System.out.println("obj ссылается на объект в куче типа С");
        if (obj instanceof D) {
            System.out.println("obj можно привести к типу D");
        } else {
            System.out.println("obj нельзя привести к типу D");
        }
        if (obj instanceof A) {
            System.out.println("obj можно привести к типу А");
        }
        System.out.println();

        // Все объекты могут быть приведены к типу Object
        if (a instanceof Object) {
            System.out.println("a можно привести к типу Object");
        }
        if (b instanceof Object) {
            System.out.println("b можно привести к типу Object");
        }
        if (c instanceof Object) {
            System.out.println("c можно привести к типу Object");
        }
        if (d instanceof Object) {
            System.out.println("d можно привести к типу Object");
        }
    }
}

class A {
    int i,j;
}

class B {
    int i,j;
}

class C extends A {
    int k;
}

class D extends A {
    int k;
}