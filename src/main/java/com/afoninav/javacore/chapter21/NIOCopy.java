package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.file.*;

/**
 * Пример копирования файла средствами NIO
 */

public class NIOCopy {
    public static void main(String[] args) {

        // Пути к файлам передаются в виде аргументов к методу main()
        if (args.length != 2) {
            System.out.println("Для корректного запуска прграммы введите:");
            System.out.println("java NIOCopy /pathToFile1/fileName1 /pathToFile2/fileName2");
            return;
        }

        try {
            Path source = Paths.get(args[0]);
            Path target = Paths.get(args[1]);

            // Копирование
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
