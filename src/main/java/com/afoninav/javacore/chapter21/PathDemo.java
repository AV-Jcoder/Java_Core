package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;

/**
 * Получить сведения о пути к файлу и о самом файле с помощью
 * интерфейса Path и класса Files.
 *
 */

public class PathDemo {
    public static void main(String[] args) {

        Path path = Paths.get("/tmp/file1.txt");

        System.out.println("Имя файла: " + path.getName(1));
        System.out.println("Путь к файлу: " + path);
        System.out.println("Абсолютгый путь к файлу: " + path.toAbsolutePath());
        System.out.println("Родительский каталог: " + path.getParent());
        System.out.println(Files.exists(path) ? "Файл существует" : "Файл не существует");

        try {
            System.out.println(Files.isHidden(path) ? "Файл скрыт" : "Файл не скрыт");
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
        System.out.println(Files.isWritable(path) ? "Файл доступен для записи" : "Файл не доступен для записи");
        System.out.println(Files.isReadable(path) ? "File is readable" : "File is not readable");

        try {
            PosixFileAttributes fileAttributes = Files.readAttributes(path, PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            System.out.println("The file group owner: " + fileAttributes.group());
            System.out.println("The file owner:" + fileAttributes.owner());
            System.out.println("Права: " + fileAttributes.permissions());
            System.out.println(fileAttributes.isDirectory() ? "Is directory" : "Is not directory");
            System.out.println(fileAttributes.isRegularFile() ? "Is regular file" : "Is not regular file");
            System.out.println(fileAttributes.isSymbolicLink() ? "Is symbolic link" : "Is not symbolic link");
            System.out.println("Creation time: " + fileAttributes.creationTime());
            System.out.println("Last mod time: " + fileAttributes.lastModifiedTime());
            System.out.println("File size bytes: " + fileAttributes.size());
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
