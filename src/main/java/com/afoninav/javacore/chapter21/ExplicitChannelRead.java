package com.afoninav.javacore.chapter21;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Использование канала ввода-вывода для чтения файла
 *
 */

public class ExplicitChannelRead {
    public static void main(String[] args) {

        int count;
        Path filePath = null;

        // Получить путь к файлу
        try {
            filePath = Paths.get("/tmp/", "file1.txt");
        } catch (InvalidPathException e) {
            System.out.println("Path error: " + e);
            return;
        }

        // Затем получить канал к этому файлу в блоке
        // оператора try-with-resources
        try (SeekableByteChannel fChan = Files.newByteChannel(filePath)) {

            // выделить память под буффер
            ByteBuffer mBuf = ByteBuffer.allocate(128);

            do {
                // читать данные из файла в буффер
                count = fChan.read(mBuf);

                // прекратить чтение по достижении конца файла
                if (count != -1) {

                    // Подготовить буфер для чтения из него данных
                    mBuf.rewind();

                    // Читать байты из буфера и выводить
                    // их на экран как символы
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) mBuf.get());
                    }
                }
            } while (count != -1);
            System.out.print(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода:" + e);
        }
    }
}
