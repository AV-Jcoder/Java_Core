package com.afoninav.javacore.chapter21;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Пример использования чтения из канала в MappedByteBuffer до версии JDK 7
 */

public class MappedChannelReadDeprecated {
    public static void main(String[] args) {
        String pathToFile = "/tmp/file1.txt";
        File file = new File(pathToFile);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
        FileInputStream fIn = null;
        FileChannel fCh = null;
        MappedByteBuffer mapBuf;

        try {
            // открыть файл для ввода
            fIn = new FileInputStream(file);

            // получить канал к файлу
            fCh = fIn.getChannel();

            // сопоставляем буффер с файлом
            mapBuf = fCh.map(FileChannel.MapMode.READ_ONLY,0,fCh.size());

            // читаем байты из буфера и выводим на экран
            for (int i = 0; i < fCh.size(); i++) {
                System.out.print((char) mapBuf.get());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        } finally {
            try {
                if (fCh != null) {
                    fCh.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception on close: " + e);
            }
            try {
                if (fIn != null) {
                    System.out.println("In finally, second try block");
                    fIn.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception on close: " + e);
            }
        }
    }
}
