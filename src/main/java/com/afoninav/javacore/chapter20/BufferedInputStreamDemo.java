package com.afoninav.javacore.chapter20;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Пример использования класса-декоратора BufferedInputStream
 */

public class BufferedInputStreamDemo {
    public static void main(String[] args) {
        String s = "This is copyright mark &copy; but &copy - is not.\n";
        byte[] buf = s.getBytes();

        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        int oneByteBuf;
        boolean marked = false;

        try (BufferedInputStream bufIn = new BufferedInputStream(in)) {
            while ((oneByteBuf = bufIn.read()) != -1) {
                switch (oneByteBuf) {
                    case '&' :
                        if (!marked) {
                            bufIn.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                        break;
                    case ';' :
                        if (marked) {
                            marked = false;
                            System.out.print("(c)");
                        } else {
                            System.out.print((char) oneByteBuf);
                        }
                        break;
                    case ' ' :
                        if (marked) {
                            marked = false;
                            bufIn.reset();
                            System.out.print("&");
                        } else {
                            System.out.print((char) oneByteBuf);
                        }
                        break;
                    default:
                        if (!marked) {
                            System.out.print((char) oneByteBuf);
                        }
                        break;
                }
            }
        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода ");
        }
    }
}
