package com.afoninav.javacore.chapter15;

import java.util.ArrayList;
import java.util.Collections;

/**
 * В примере используется ссылка на метод, чтобы найти
 * максимальное значение в коллекции.
 *
 * В данном примере нужно обратить внимание на то, что в самом классе MyClass
 * не определяется метод сравнения и не реализуется интерфейс Comparator.
 *
 * Но максимальное значение из списка объектов MyClass может быть получено в результате вызова метода max,
 * так как в классе UseMethodRef определяется статический метод compareMC() совместимый с методом compare(),
 * определённым в интерфейсе Comparator.
 *
 */

public class UseMethodRef {
    // Метод compareMC(), совместимый с аналогичным методом,
    // определённым в интерфейсе Comparator<T>
    static int compareMC(MyClass a, MyClass b) {
        return a.getVal() - b.getVal();
    }

    public static void main(String[] args) {
        ArrayList<MyClass> al = new ArrayList<MyClass>();

        al.add(new MyClass(1));
        al.add(new MyClass(4));
        al.add(new MyClass(2));
        al.add(new MyClass(9));
        al.add(new MyClass(3));
        al.add(new MyClass(7));

        // Найти максимальное значение, используя метод compareMC()
        MyClass maxValObj = Collections.max(al, UseMethodRef::compareMC);
        System.out.println("Максимальное значение равно: " + maxValObj.getVal());
    }
}

class MyClass {
    private int val;

    MyClass(int v) {
        val = v;
    }

    int getVal() {
        return this.val;
    }
}
