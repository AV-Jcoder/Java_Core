package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример открытия канала типа FileChannel и
 * создания буфера типа MappedByteBuffer
 */

public class MappedChannelRead {
    public static void main(String[] args) {

        Path filePath = Paths.get("/tmp/file1.txt");

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("Ошибка создания файла: " + e);
        }

        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(filePath)) {

            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,0, fileChannel.size());

            for (int i = 0; i < byteBuffer.capacity(); i++) {
                System.out.print((char) byteBuffer.get());
            }
            System.out.println("");
        } catch (InvalidPathException e) {
            System.out.println("Неверный путь: " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }


    }
}
