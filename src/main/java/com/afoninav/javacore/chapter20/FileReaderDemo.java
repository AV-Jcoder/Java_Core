package com.afoninav.javacore.chapter20;

import java.io.FileReader;
import java.io.IOException;

/**
 * Пример применения класса FileReader
 *
 */

public class FileReaderDemo {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("FileReaderDemo.java")) {
            int c;
            while ((c = fileReader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println("Input-Output error: " + e);
        }
    }
}
