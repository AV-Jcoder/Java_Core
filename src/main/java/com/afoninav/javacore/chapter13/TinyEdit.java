package com.afoninav.javacore.chapter13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TinyEdit {
    public static void main(String[] args) throws IOException {
        // создаём поток ввода типа BufferedReader,
        // используя стандартный поток ввода System.in
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = new String[100];
        System.out.println("Введите строку с текстом.");
        System.out.println("Введите \"стоп\" для завершения.");
        for (int i = 0; i < 100; i++) {
            strings[i] = br.readLine();
            if(strings[i].equals("стоп")){
                break;
            }
        }
        System.out.println("Содержимое вашего файла:");
        // выводим текстовые строки
        for (int i = 0; i < 100; i++) {
            if (strings[i].equals("стоп")){
                break;
            }
            System.out.println(strings[i]);
        }
    }
}
