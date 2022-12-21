package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

/**
 * Пример программы для просмотра содержимого файла.
 *
 * Открытие байтового потока InputStream
 * с помощью системы NIO.
 *
 */

public class ShowFile {
    public static void main(String[] args) {
        // Имена файлов передаются в аргументах метода main()
        if (args.length != 1) {
            System.err.println("Ошибка запуска программы.");
            System.err.println("Для запуска программы введите:");
            System.err.println("java ShowFile /pathToFile1/sourceFileName");
            return;
        }
        // Создать путь
        Path path = Paths.get(args[0]);
        // Открываем файл и создаём поток байтов
        try (InputStream in = Files.newInputStream(path, StandardOpenOption.READ)) {
            int b;
            // Читаем из файла байты
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
