package com.afoninav.javacore.chapter20;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Пример применения класса FileInputStream и
 * оператора try-with-resources.
 *
 */

public class FileInputStreamDemo {
    public static void main(String[] args) {
        int size;

        // Для автоматического закрытия потоков
        // ввода вывода используется try-with-resources
        String pathToFile = "/pathToFile";
        try(FileInputStream fis = new FileInputStream(pathToFile)) {
            System.out.println("Общее количество доступных байт:" + (size = fis.available()));
            int n = size / 40;
            System.out.printf("Первые %d байт, прочитанные из файла по очереди методом read() \n", n);
            for (int i = 0; i < n; i++) {
                System.out.print((char)fis.read());
            }
            System.out.printf("\nВсё еще доступно %d байт. \n", fis.available());



            System.out.printf("Чтение следующих %d байт методом read(byte[] b) \n",  n);
            byte[] b = new byte[n];
            int i;
            if ( (i = fis.read(b)) != n) {
                System.err.printf("Нельзя прочитать %d байтов. Всего прочитано %d байт \n",n,i);
            }
            String line = new String(b, 0, n);
            System.out.println(line);
            System.out.printf("Всё еще доступно %d байт. \n",fis.available());



            System.out.println("Пропуск половины оставшихся методом skip()");
            fis.skip(size/2);
            System.out.printf("Всё еще доступно %d байт. \n", fis.available());



            System.out.printf("Чтение %d байт размещённых в конце массива.\n", n/2);
            if ((i = fis.read(b,n/2,n/2)) != n/2) {
                System.err.printf("Нельзя прочитать %d байтов. Всего прочитано %d байт \n",n/2,i);
            }
            line = new String(b,n/2,n/2);
            System.out.println(line);
            System.out.printf("Всё еще доступно %d байт. \n",fis.available());

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }
}
