package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

/**
 * Пример записи в файл с использованием MappedByteBuffer.
 * Особенность этого буфера в том, что записанные в буфер данные
 * сразу отображаются в файле(данные пишутся и в буфер и в файл неявным оразом).
 *
 */

public class MappedChannelWrite {
    public static void main(String[] args) {
        // Создать путь к файлу
        Path path = Paths.get("/tmp/file2.txt");
        // Получить канал к файлу в блоке try-with-resources
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(path, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,StandardOpenOption.READ)) {
            // Сопоставляем файл с буфером
            MappedByteBuffer mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,26);
            // Записываем заданное количество байт в буффер
            for (int i = 0; i < 26; i++) {
                mappedBuffer.put((byte) ('A' + i));
            }
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e);
        } catch (IOException e) {
            System.out.println("IO exception:" + e);
        }
    }
}
