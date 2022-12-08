package com.afoninav.javacore.chapter20;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Пример применения класса FileOutputStream с простым
 * вариантом высвобождения ресурсов.
 *
 */

public class FileOutputStreamDemo {
    public static void main(String[] args) {
        String source = "Now the time for all good man\n" +
                " to come to the aid of their country\n" +
                " and pay their due taxes.";
        byte[] buff = source.getBytes();
        FileOutputStream fos1 = null;
        FileOutputStream fos2 = null;
        FileOutputStream fos3 = null;
        try {
            fos1 = new FileOutputStream("/file1.txt");
            fos2 = new FileOutputStream("/file2.txt");
            fos3 = new FileOutputStream("/file3.txt");
            // Запись в первый файл каждого второго байта
            for (int i = 0; i < buff.length; i+=2) {
                fos1.write(buff[i]);
            }
            // Запись во второй файл всех байт
            fos2.write(buff);
            // Запись в третий файл только последнюю 1/4 абзаца
            fos3.write(buff, buff.length-buff.length/4, buff.length/4);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        } finally {
            try {
                if(fos1 != null) fos1.close();
            } catch (IOException e) {
                System.out.printf("Ошибка закрытия file1");
            }
            try {
                if(fos2 != null) fos2.close();
            } catch (IOException e) {
                System.out.printf("Ошибка закрытия file2");
            }
            try {
                if(fos3 != null) fos3.close();
            } catch (IOException e) {
                System.out.printf("Ошибка закрытия file3");
            }
        }
    }
}
