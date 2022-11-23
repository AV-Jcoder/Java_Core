package com.afoninav.javacore.chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Программа копирует содержимое из одного файла
 * в другой файл.
 *
 * Внимание! В этом примере используется try-with-resources
 * для открытия потока чтения и для потока записи
 *
 */

public class CopyFileWithNewTry {
    public static void main(String[] args) {
        // убедимся, что в аргументах передаются оба имени файла
        if (args.length != 2) {
            System.out.println("Для запуска программы введите:");
            System.out.println("java CopyFileWithNewTry /pathToFile1/fileName1 /pathToFile2/fileName2");
            return;
        }
        // открываем два файла через try-with-resources
        int buffer;
        try (FileInputStream fin = new FileInputStream(args[0]);
             FileOutputStream fout = new FileOutputStream(args[1])) {
            while ((buffer = fin.read()) != -1) {
                fout.write(buffer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно открыть файлы: " + e);
        } catch (IOException e) {
            System.out.println("Ошибка чтения-записи файла:" + e);
        }
    }
}