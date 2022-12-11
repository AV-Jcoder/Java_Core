package com.afoninav.javacore.chapter20;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Огранизация последовательного ввода потков с помощью
 * класса SequenceInputStream
 */

public class SequenceInputStreamDemo {
    public static void main(String[] args) {

        Vector<String> files = new Vector<String>();
        files.addElement("/tmp/file1.txt");
        files.addElement("/tmp/file2.txt");
        files.addElement("/tmp/file3.txt");

        InputStreamEnumerator ise = new InputStreamEnumerator(files);
        // Вторая форма конструктора, который в качестве параметра принимает
        // объект, реализующий Enumeration<? extends InputStream>
        InputStream is = new SequenceInputStream(ise);

        int oneByte;
        try {
            while ((oneByte = is.read()) != -1) {
                System.out.print((char) oneByte);
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка открытия файла: " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                System.out.println("Ошибка закрытия потока ввода SequenceInputStream");
            }
        }
    }
}


/**
 * Класс реализует interface Enumeration<? extends InputStream> для передачи
 * экземпляра этого класса в конструктор SequenceInputStream.
 *
 * Enumerator -это интерфейс по типу Iterator, даже методы схожи.
 */

class InputStreamEnumerator implements Enumeration<FileInputStream> {

    private Enumeration<String> files;

    public InputStreamEnumerator(Vector<String> files) {
        // метод elements() класса Vector возвращает ссылку
        // на объект Enumerator, который может перемещаться по коллекции.
        this.files = files.elements();
    }

    @Override
    public boolean hasMoreElements() {
        return files.hasMoreElements();
    }

    @Override
    public FileInputStream nextElement() {
        try {
            return new FileInputStream(files.nextElement().toString());
        } catch (IOException e) {
            return null;
        }
    }
}
