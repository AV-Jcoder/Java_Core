package com.afoninav.javacore.chapter20;

import java.io.ByteArrayInputStream;

/**
 * Пример использования класса ByteArrayInputStream
 */

public class ByteArrayInputStreamDemo {
    public static void main(String[] args) {
        String tmp = "abcdefghijklmnopqrstuvwxyz";
        byte[] buffer = tmp.getBytes();
        // Передаём весь алфавит
        ByteArrayInputStream input1 = new ByteArrayInputStream(buffer);
        // Передаём только первые 3 буквы
        ByteArrayInputStream input2 = new ByteArrayInputStream(buffer, 0, 3);
    }
}
