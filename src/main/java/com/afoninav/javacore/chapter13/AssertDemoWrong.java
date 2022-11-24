package com.afoninav.javacore.chapter13;

/**
 * Демонстрация оператора assert
 *
 * в данном примере переменной
 * n присваивается значение в строке оператора assert,
 * и при отключёной опции -ea нарушается логика программы.
 *
 * Так делать не нужно.
 *
 */
public class AssertDemoWrong {
    static int val = 3;
    // возвратить целочисленное значение
    static int getNum() {
        return val--;
    }

    public static void main(String[] args) {
        int n = 0;

        for (int i = 0; i < 10; i++) {

            assert (n = getNum()) > 0 : "n > 0 ? false"; // false при n == 0
            System.out.println("n равно " + n);
        }
    }
}
