package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;


/**
 * Пример записи английского алфавита в файл через канал.
 *
 */

public class ExplicitChannelWrite {
    public static void main(String[] args) {
        // Создать путь к файлу.
        Path path = Paths.get("/tmp/file2.txt");
        // Создаём канал и открываем файл в блоке try-with-resources.
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(path,
                StandardOpenOption.CREATE,StandardOpenOption.WRITE)) {
            // Создаём буфер
            ByteBuffer byteBuffer = ByteBuffer.allocate(26);

            // Записываем байты в буффер.
            for (int i = 0; i < 26; i++) {
                byteBuffer.put((byte) ('A' + i));
            }
            // Подготовим буфер у записи.
            byteBuffer.flip();
            // Записываем данные из буфера в канал
            fileChannel.write(byteBuffer);
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " +e);
        } catch (IOException e) {
            System.out.println("IO Exception: " + e);
        }
    }
}
