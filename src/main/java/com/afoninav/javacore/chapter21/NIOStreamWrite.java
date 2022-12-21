package com.afoninav.javacore.chapter21;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;

/**
 * Пример вызова байтового потока OutputStream через NIO,
 * для записи английского алфавита в файл.
 *
 */

public class NIOStreamWrite {
    public static void main(String[] args) {

        // Открыть файл и получить связанный с ним поток вывода
        try (OutputStream out = Files.newOutputStream(Paths.get("/tmp/file2.txt"), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
             // Оборачиваем в буферизированный поток
             BufferedOutputStream bufOut = new BufferedOutputStream(out)) {

            // Записываем в поток символы алфавита
            for (int i = 0; i < 26; i++) {
                bufOut.write('A' + i);
            }
            bufOut.flush();
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
