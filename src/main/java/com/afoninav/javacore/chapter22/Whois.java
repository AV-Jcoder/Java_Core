package com.afoninav.javacore.chapter22;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Пример использования сокетов.
 *
 */

public class Whois {
    public static void main(String[] args) {

        // создаём сокетное соединение с сайтом www.internic.net
        // через порт 43

        try (Socket socket = new Socket("whois.internic.net",43)) {

            System.out.println("In try block");
            // получить потоки ввода-вывода
            InputStream is = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // сформировать строку запроса
            String siteName = "www.youtube.com\n";

            // преобразовать строку в байты
            byte[] buf = siteName.getBytes();

            System.out.println("отправляем запрос");
            // послать запрос
            out.write(buf);
            System.out.println("отправлено");

            // прочитать ответ и вывести его на экран
            System.out.println("Доступно байт " + is.available());
            int c;
            while (is.available() > 0) {
                c = is.read();
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }
    }
}
