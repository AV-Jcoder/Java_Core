package com.afoninav.javacore.chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFileWithFinallyVer2 {
    public static void main(String[] args) {

        FileInputStream fin = null;
        // Убедимся, что имя файла указано во входящем параметре метода main
        if (args.length != 1) {
            System.out.println("Используйте следующую команду: java ShowFileWithFinallyVer2 /pathToFile/fileName");
            return;
        }

        // В следующем коде открывается файл
        // затем из него читаются символы? или байты? до тех пор,
        // пока не встретится признак конца файла
        int i;
        try {
            fin = new FileInputStream(args[0]);
            do {
                i = fin.read();
                if (i != -1) {
                    System.out.println(i);
                }
            } while (i != -1);
        }
//        catch (FileNotFoundException e) {
//            System.out.println("Невозможно открыть файл.");
//        }
        catch (IOException e) {
            System.out.println("Ошибка ввода - вывода.");
        } finally {
            try {
                if(fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка закрытия файла");
            }
        }
    }
}
