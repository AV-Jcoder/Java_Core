package com.afoninav.javacore.chapter21;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Пример записи английского алфавита
 * в файл с помощью NIO до JDK ver.7
 *
 */

public class ExplicitChannelWriteDeprecated {
    public static void main(String[] args) {
        String outFileDirName = "/tmp/file2.txt";
        File outFile = new File(outFileDirName);
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        ByteBuffer byteBuffer;
        try {
            // открываем файл и поток
            fileOutputStream = new FileOutputStream(outFile);
            // открываем канал
            fileChannel = fileOutputStream.getChannel();
            // создаём буфер
            byteBuffer = ByteBuffer.allocate(26); // 26 английских символов
            // записываем некоторое количество байт в буффер
            for (int i = 0; i < byteBuffer.capacity(); i++) {
                byteBuffer.put((byte) ('A' + i));
            }
            // готовим буфер к записи
            byteBuffer.flip();
            // записываем данные из буфера в выходной файл
            fileChannel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        } finally {
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception on close: " + e);
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception on close: " + e);
            }
        }
    }
}
