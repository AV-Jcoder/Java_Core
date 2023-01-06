package com.afoninav.javacore.chapter22;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Пример применения класса URLConnection.
 *
 */

public class URLConnectionDemo {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.internic.net:80/index.html");
        URLConnection urlc = url.openConnection();

        // Получить дату ответа сервера
        long timeMillis = urlc.getDate();
        if (timeMillis == 0) {
            System.out.println("Сведения о дате отсутствуют.");
        } else {
            System.out.println("Дата ответа: " + new Date(timeMillis));
        }

        // Получить тип содержимого
        System.out.println("Тип содержимого: " + urlc.getContentType());

        // Получить дату срока действия ресурса
        timeMillis = urlc.getExpiration();
        if (timeMillis == 0) {
            System.out.println("Сведения о сроке действия отсутствуют.");
        } else {
            System.out.println("Срок действия истекает: " + new Date(timeMillis));
        }

        // Получить дату последней модификации
        timeMillis = urlc.getLastModified();
        if (timeMillis == 0) {
            System.out.println("Сведения о дате последней модификации отсутствуют.");
        } else {
            System.out.println("Дата модификации: " + new Date(timeMillis));
        }

        // Получить длинну содержимого
        long len = urlc.getContentLengthLong();
        if (len == -1) {
            System.out.println("Длинна содержимого недлступна.");
        } else {
            System.out.println("Длина содержимого в байтах: " + len);
        }

        // Отобразить содержимое
        int oneByte;
        if (len != -1) {
            System.out.println("===Content===");
            InputStream in = urlc.getInputStream();
            while ((oneByte = in.read()) != -1) {
                System.out.print((char) oneByte);
            }
            in.close();
        } else {
            System.out.println("Содержимое недоступно.");
        }
    }
}
