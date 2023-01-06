package com.afoninav.javacore.chapter22;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Пример использования класса HTTPURLConnection
 */

public class HTTPURLConnectionDemo {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.google.com:443");
        HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();

        // вывести метод запроса
        System.out.println("Метод запроса: " + httpURLCon.getRequestMethod());

        // вывести код ответа
        System.out.println("Код ответа: " + httpURLCon.getResponseCode());

        // вывести ответное сообщение
        System.out.println("Ответное сообщение: " + httpURLCon.getResponseMessage());

        // получить список полей и множество ключей из заголовка
        Map<String, List<String>> httpUrlConnectMap = httpURLCon.getHeaderFields();
        Set<String> headerFields = httpUrlConnectMap.keySet();
        System.out.println("Заголовки:");
        for (String value : headerFields) {
            System.out.printf("Название заголовка: %s \nЗначение заголовка: %s\n==============================\n", value, httpUrlConnectMap.get(value));
        }
    }
}
