package com.afoninav.javacore.chapter20;

import java.io.File;

/**
 * Пример применения некоторых методов из класса File.
 *
 */

public class FileDemo {
    static void p(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        File f1 = new File("/tmp/","temp.txt");
        p("Имя файла: " + f1.getName());
        p("Путь: " + f1.getPath());
        p("Абсолютный путь: " + f1.getAbsolutePath());
        p("Родительский каталог: " + f1.getParent());
        p(f1.exists() ? "Файл существует" : "Файл не существует");
        p(f1.canWrite() ? "Доступен для записи" : "Не доступен для записи");
        p(f1.canRead() ? "Доступен для чтения" : "Не доступен для чтения");
        p(f1.isDirectory() ? "Является каталогом" : "Не является каталогом");
        p(f1.isFile() ? "Является обычным файлом" : "Может быть именнованным каналом");
        p(f1.isAbsolute() ? "Является абсолютным" : "Не является абсолютным");
        p("Последнее изменение в файле " + f1.lastModified());
        p("Размер " + f1.length() + " байт");
        p("Количество свободных гигобайт в разделе " + f1.getFreeSpace()/1024/1024/1024);
        p("Всего гигобайт в разделе " + f1.getTotalSpace()/1024/1024/1024);
        p("Количество доступного места в разделе,  гигобайт " + f1.getUsableSpace()/1024/1024/1024);
        p("Теперь файл доступен долько для чтения? " + f1.setReadOnly());
    }
}
