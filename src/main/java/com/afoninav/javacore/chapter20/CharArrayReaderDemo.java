package com.afoninav.javacore.chapter20;

import java.io.CharArrayReader;
import java.io.IOException;

/**
 * Пример применения класса CharArrayReader
 */

public class CharArrayReaderDemo {
    public static void main(String[] args) {
        String tmp = "abcdefghijklmnopqastuvwxyz";
        int length = tmp.length();
        char[] c = new char[length];
        tmp.getChars(0, length, c, 0);

        try (CharArrayReader charArrayReader = new CharArrayReader(c)) {
            System.out.println("input1:");
            int i;
            while ((i = charArrayReader.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("");
        } catch (IOException e) {
            System.out.println("IO error:" + e);
        }

        try (CharArrayReader charArrayReader2 = new CharArrayReader(c, 0, 5)) {
            System.out.println("input2:");
            int i;
            while((i = charArrayReader2.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("");
        } catch (IOException e) {
            System.out.println("Input-Output error:" + e);
        }
    }
}
