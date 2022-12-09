package com.afoninav.javacore.chapter20;

import java.io.ByteArrayInputStream;

/**
 * Пример использования класса ByteArrayInputStream и
 * метода reset(), который устанавливает потом ввода
 * в исходное состояние.
 *
 */

public class ByteArrayInputStreamReset {
    public static void main(String[] args) {
        String abc = "abc";
        byte[] buffer = abc.getBytes();

        ByteArrayInputStream is = new ByteArrayInputStream(buffer);

        for (int i = 0; i < 2; i++) {
            int oneByte;
            while((oneByte = is.read()) != -1) {
                if (i == 0) {
                    System.out.print((char) oneByte);
                } else {
                    System.out.print(Character.toUpperCase((char)oneByte));
                }
            }
            System.out.println("");
            is.reset();
        }
    }
}
