package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Обход дерева каталогов с помощью метода
 * Files.walkFileTree(Path root, FileVisitor<? extends Path> fv)
 *
 * Интерфейс FileVisitor опеределяет способ обхода каталогов.
 *
 */

public class DirTreeList {
    public static void main(String[] args) {

        String pathName = "/home/alex-deb/Загрузки/";
        Path path = null;
        try {
            path = Paths.get(pathName);
        } catch (InvalidPathException e) {
            System.out.println("Invalid path:" + e);
            return;
        }

        FileVisitor<Path> visitor = new MyFileVisitor();
        System.out.println("Start");
        try {
            Files.walkFileTree(path, visitor);
        } catch (IOException e) {
            System.out.println("IO exception:" + e);
        }
    }
}

// Создаём собственную версию FileVisitor
class MyFileVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        System.out.println(path);
        return FileVisitResult.CONTINUE;
    }
}



