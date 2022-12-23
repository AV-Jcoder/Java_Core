package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *  Получение содержимого каталога с помощью метода определённого в класса Files
 *  newDirectoryStream(Path path, DirectoryStream.Filter<? super Path> filter)
 *
 * Вывести все файлы из каталога, доступные для записи
 *
 */

public class DirListVer2 {
    public static void main(String[] args) {

        String dirName = "/tmp/";
        Path path;
        try {
            path = Paths.get(dirName);
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e);
            return;
        }

        // создать фильтр возвращающий логическое значение true
        // если файл доступен для записи
        DirectoryStream.Filter<Path> how = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isWritable(entry);
            }
        };

        // Теперь получаем поток ввода из каталога
        // только доступных для записи файлов
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, how)) {
            System.out.println("Каталог: " + path);

            for (Path entry : directoryStream) {
                BasicFileAttributes attrib = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attrib.isDirectory()) {
                    System.out.print("<DIR> ");
                } else {
                    System.out.print("      ");
                }
                System.out.println(entry.getName(1));
            }
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
