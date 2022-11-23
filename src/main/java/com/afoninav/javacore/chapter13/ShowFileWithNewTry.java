package com.afoninav.javacore.chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * В этом примере применяется оператор try c ресурсами для
 * автоматического закрытия файла.
 */

public class ShowFileWithNewTry {
    public static void main(String[] args) {
        // Убедимся в правильности имени файла
        if(args.length != 1) {
            System.out.println("Для запуска программы введите:");
            System.out.println("java ShowFileWithNewTry /pathToFile/fileName");
            return;
        }
        // Ниже оператор try c ресурсами применяется сперва
        // для открытия, а затем для автоматического закрытия
        // файла по завершения блока оператора.
        int buffer = 0;
        try (FileInputStream fin = new FileInputStream(args[0])) {
            while((buffer = fin.read()) != -1) {
                System.out.print((char) buffer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
