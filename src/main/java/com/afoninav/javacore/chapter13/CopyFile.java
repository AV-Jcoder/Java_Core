package com.afoninav.javacore.chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Копирование файла. Чтобы воспользоваться этой
 * программой нужно указать имена исходного и
 * целевого файла.
 * Например, чтобы скопировать файл FIRST.txt в
 * файл SECOND.txt нудно ввести в консоли следующую
 * команду:
 * java CopyFile /pathToFile1/fileName1.txt /pathToFile2/fileName2.txt
 */

public class CopyFile {
    public static void main(String[] args) {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        // Убедимся, что указаны имена обоих файлов
        if (args.length != 2) {
            System.out.println("Неверные параметры запуска программы.");
            System.out.println("Для запуска программы введите:");
            System.out.println("java CopyFile /pathToFile1/fileName1.txt /pathToFile2/fileName2.txt");
            return;
        }

        // копирование файла
        int i = 0;
        try {
            // попытка открыть файлы
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);
            do {
                i = fis.read();
                if (i != -1) {
                    fos.write(i);
                }
            } while (i != -1);
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно открыть файлы");
        } catch (IOException e) {
            System.out.println("Ошиюка ввода-вывода");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    System.out.println("Закрываем FileInputStream");
                    throw new IOException("Внезапная ошибка.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии InputStream:" + e);
            }
            // Этот try выполнится в любом случае, даже если в стороке 54 будет ошибка.
            try {
                if (fos != null) {
                    fos.close();
                    System.out.println("Закрываем FileOutputStream");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии OutputStream");
            }
        }
    }
}
