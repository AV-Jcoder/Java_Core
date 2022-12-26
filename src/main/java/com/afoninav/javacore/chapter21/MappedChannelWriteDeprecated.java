package com.afoninav.javacore.chapter21;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Пример записи в файл английского алфавита
 * с помощью NIO до версии JDK.7
 * c использованием класса MappedByteBuffer и класса
 * RandomAccessFile.
 *
 */

public class MappedChannelWriteDeprecated {
    public static void main(String[] args) {
        String filePathName = "/tmp/file2.txt";
        RandomAccessFile randomAccessFile = null;
        FileChannel fileChannel = null;
        MappedByteBuffer mappedByteBuffer = null;
        try {
            // открываем файл
            randomAccessFile = new RandomAccessFile(filePathName, "rw");
            // открываем канал
            fileChannel = randomAccessFile.getChannel();
            // сопоставляем файл с буфером байт
            // в методе map() 0 - стартовая позиция в файле, 26 - отображаемый размер в буфер
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,26);
            // записываем в буфер данные
            System.out.println(mappedByteBuffer.capacity());
            for (int i = 0; i < 26; i++) {
                mappedByteBuffer.put((byte) ('A' + i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
