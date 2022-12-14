package com.afoninav.javacore.chapter20;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

/**
 * Пример применения класса BufferedReader
 */

public class BufferedReaderDemo {
    public static void main(String[] args) {
        String s = "Это знак авторского права &copy; , а &copy это нет.\n";
        char[] buff = s.toCharArray();

        CharArrayReader in = new CharArrayReader(buff);
        int c;
        boolean marked = false;

        try (BufferedReader f = new BufferedReader(in)) {

            while ((c = f.read()) != -1) {
                switch (c) {
                    case '&' :
                        if (!marked) {
                            f.mark(32);
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
                            System.out.print((char) c);
                        }
                        break;
                    case ' ' :
                        if (marked) {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        } else {
                            System.out.print((char) c);
                        }
                        break;
                    default:
                        if (!marked) {
                            System.out.print((char) c);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
