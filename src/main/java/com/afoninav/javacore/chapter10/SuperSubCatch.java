package com.afoninav.javacore.chapter10;

/**
 * Правильный порядок операторов catch
 */
public class SuperSubCatch {
    public static void main(String[] args) {
        try {
            int a = 0;
            int b = 42 / a;
        } catch (ArithmeticException e) {
            System.out.println("Этот код недостижим и считается ошибкой.");
        } catch (Exception e) {
            System.out.println("Перехват исключения общего класса Exception.");
        }
    }
}
