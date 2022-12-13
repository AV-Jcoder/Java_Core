package com.afoninav.javacore.chapter20;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Пример применения класса FileWriter
 */

public class FileWriterDemo {
    public static void main(String[] args) {
        String source = "Now is a time for all good man\n" +
                "to come to aid of their country\n" +
                "and pay their due taxes.";
        char[] buffer = new char[source.length()];
        source.getChars(0,source.length(), buffer, 0);

        try (FileWriter fileWriter1 = new FileWriter("/tmp/file1.txt");
            FileWriter fileWriter2 = new FileWriter("/tmp/file2.txt");
            FileWriter fileWriter3 = new FileWriter("/tmp/file3.txt")) {

            // Вывести каждый второй символ в перый файл.
            for (int i = 0; i < buffer.length; i+=2) {
                fileWriter1.write(buffer[i]);
            }

            // Вывести все символы во второй файл
            fileWriter2.write(buffer);

            // Вывести последнюю четверть символов в третий файл
            fileWriter3.write(buffer, buffer.length - buffer.length/4, buffer.length/4);
        } catch (IOException e) {
            System.out.println("IO error:" + e);
        }
    }
}
