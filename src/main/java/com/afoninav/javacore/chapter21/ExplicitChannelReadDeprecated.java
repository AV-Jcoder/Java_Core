package com.afoninav.javacore.chapter21;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  Пример использования канала для чтения из файла
 *  до версии JDK 7.
 */


public class ExplicitChannelReadDeprecated {
    public static void main(String[] args) {

        String pathToFile = "/tmp/file1.txt";
        File file = new File(pathToFile);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Cant create file: " + e);
        }
        FileInputStream fin = null;
        FileChannel fch = null;
        ByteBuffer byteBuffer;

        try {
            // открыть файл для ввода
            fin = new FileInputStream(file);

            // получаем канал к файлу
            fch  = fin.getChannel();

            // выделяем память под буффер
            byteBuffer = ByteBuffer.allocate(16);

            // читать данные в буффер
            int byteCount;
            do {
                // читать данные из канала в буффер
                byteCount = fch.read(byteBuffer);

                // извлекать из буфера данные пока не будет достугнут конец файла
                if (byteCount == -1) {

                    // подготовить буффер к иззвлечению из него данных
                    byteBuffer.flip();

                    // выводить байты из буфера на экран в виде символов
                    for (int i = 0; i < byteBuffer.limit(); i++) {
                        System.out.print((char) byteBuffer.get(i));
                    }
                }
            } while (byteCount != -1);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        } finally {
            try {
                if (fch != null) {
                    fch.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception on close: " + e);
            }
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception on close: " + e);
            }
        }
    }
}
