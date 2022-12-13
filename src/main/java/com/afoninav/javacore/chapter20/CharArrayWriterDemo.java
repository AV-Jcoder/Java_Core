package com.afoninav.javacore.chapter20;

import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Пример применения класса CharArrayWriter
 */

public class CharArrayWriterDemo {
    public static void main(String[] args) {
        CharArrayWriter f = new CharArrayWriter();
        String s = "Эти данные должны быть выведены в массив";
        char[] buf = new char[s.length()];

        // сохраняем строку в массив символов char[] buf
        s.getChars(0,s.length(), buf, 0);

        try {
            f.write(buf);
        } catch (IOException e) {
            System.out.println("Input - Output error");
            return;
        }

        System.out.println("Содержимое буфера в виде строки");
        System.out.println(f.toString());

        System.out.println("Сохранение внутреннего буфера класса CharArrayWriter в массив символов");
        char[] buff = f.toCharArray();
        for (int i = 0; i < buff.length; i++) {
            System.out.print(buff[i]);
        }

        System.out.println("\nПередача внутреннего буффера в поток вывода символов FileWriter");
        try (FileWriter fileWriter = new FileWriter("/tmp/file1.txt");
             FileWriter fileWriter2 = new FileWriter("/tmp/file2.txt")) {
            f.writeTo(fileWriter);
            f.writeTo(fileWriter2);
        } catch (FileNotFoundException e) {
            System.out.println("Can`t open file: " + e);
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }

        System.out.println("Сброс длинны буфера в ноль. И заполнение его тремя символами ХХХ");
        f.reset();
        for (int i = 0; i < 3; i++) {
            f.write('X');
        }
        System.out.println(f.toString());
    }
}
