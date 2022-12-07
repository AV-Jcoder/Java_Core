package com.afoninav.javacore.chapter20;

import java.io.File;
import java.io.FileFilter;

/**
 * Пример, где из директории отфильтровываются все файлы кроме
 * файлов с расширением .txt с помощью функционального интерфейса
 * FileFilter и лямбда выражения.
 *
 */

public class TxtListOnly {
    public static void main(String[] args) {
        String dir = new String("/tmp/");
        File file1 = new File(dir);

        if (file1.isDirectory()) {
            System.out.println(file1.getName() + " является директорией.");
            FileFilter txtFileFilter = (n) -> n.getAbsolutePath().endsWith(".txt");
            File[] filesList = file1.listFiles(txtFileFilter);
            for (File f :  filesList) {
                System.out.println(f.getName());
            }
        } else {
            System.out.println(file1.getName() + " не является директорией.");
        }
    }
}
