package com.afoninav.javacore.chapter13;

/**
 * Демонстрация оператора assert
 *
 * для запуска через консоль:
 * java -ea
 *
 * для запуска в idea указать
 * в спец грфае VM options -ea
 *
 */
public class AssertDemo {
    static int val = 3;
    // возвратить целочисленное значение
    static int getNum() {
        return val--;
    }

    public static void main(String[] args) {
        int n;

        for (int i = 0; i < 10; i++) {
            n = getNum();
            assert n > 0 : "n > 0 ? false"; // false при n == 0
            System.out.println("n равно " + n);
        }
    }
}
