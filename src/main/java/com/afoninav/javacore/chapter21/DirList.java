package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Получение содержимого каталога /tmp/ с помощью объекта
 * DirectoryStream<T> реализующего интерфейс Iterable<T>.
 *
 */

public class DirList {
    public static void main(String[] args) {

        String dirName = "/tmp/";

        // Получить и обработать поток ввода
        // в болке оператора try
        Path path = Path.of(dirName);
        try (DirectoryStream<Path> dirStream =
                     Files.newDirectoryStream(path)) {

            for (Path entity : dirStream) {
                boolean isFile = Files.isDirectory(entity);
                System.out.println(isFile ? "      " + entity.getFileName() : "<DIR> " + entity   );
            }




        } catch (InvalidPathException e) {
            System.out.println("Invalid exception: " + e);
        } catch (NotDirectoryException e) {
            System.out.println("Not directory exception: " + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
