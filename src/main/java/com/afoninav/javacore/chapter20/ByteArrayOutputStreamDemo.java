package com.afoninav.javacore.chapter20;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Пример использования класса ByteArrayOutputStream
 * его методов toByteArray() - возвращает копию внутреннего буфера
 * writeTo(OutputStream out) - аналогичен вызову метода out.write(buffer,0,count) где buffer -
 * это внутренный буфер.
 */

public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String line = "Эти данные должны быть выведены в массив";
        byte[] buffer = line.getBytes();

        try {
            byteArrayOutputStream.write(buffer);
        } catch (IOException e) {
            System.out.println("Ошибка записи в буффер: " + e);
        }

        System.out.println("Буффер в виде символьной строки:");
        System.out.println(byteArrayOutputStream.toString());

        System.out.println("Сохраняем буффер в массив:");
        byte[] b = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < b.length; i++) {
            System.out.print((char) b[i]);
        }

        System.out.println("\nПередаём ByteArrayOutputStream в поток вывода ");
        try (FileOutputStream fileOutputStream = new FileOutputStream("/tmp/2.txt")){
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода: " + e);
            return;
        }
        System.out.println("Установка в исходное состояние:");
        byteArrayOutputStream.reset();
        for (int i = 0; i < 3; i++) {
            byteArrayOutputStream.write('X');
        }
        System.out.println(byteArrayOutputStream.toString());
    }
}
