package com.afoninav.javacore.chapter22;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Пример применения класса URL
 *
 * метод getPort() вовращает -1 т.к. порт явно не установлен.
 *
 * Чтобы получить доступ к информационному наполнению объекта URL
 * нужно создать объект URLConnection вызвав метод объекта url.openConnection()
 *
 */

public class URLDemo {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.google.com/index.html");
        System.out.println("Protocol: " + url.getProtocol());
        System.out.println("Host: " + url.getHost());
        System.out.println("Port: " + url.getPort());
        System.out.println("File: " + url.getFile());
        System.out.println("Full: " + url.toExternalForm());

        URLConnection urlc = url.openConnection();
    }
}
