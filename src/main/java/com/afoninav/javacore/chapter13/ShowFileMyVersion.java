package com.afoninav.javacore.chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFileMyVersion {
    public static void main(String[] args) throws FileNotFoundException {

        // Конвертируем символ Б в байты
        System.out.println((int) 'Б');
        // Получаем первые 8 бит
        System.out.println(1041 % 256);
        // Получаем вторые 8 бит
        System.out.println(1041 / 256);
        byte a = 17;
        short b = 4 << 8;
        // Конвертируем обратно в символ
        char result = (char) (a | b);
        // Проверяем результат
        System.out.println(result);



        FileInputStream fin;
        // убедимся, что имя файла  указано
        if (args.length != 1) {
            System.out.println("Введите в командной строке : java ShowFile /path/file_name");
            return;
        }
        // Попытка открыть файл
        try {
            fin = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно открыть файл");
            return;
        }
        // Теперь файл открыт и готов к чтению.
        // Теперь из него читаются символы до тех пор,
        // пока не встретится признак конца файла




        // Вот тут непонятно что происходит
        try {
            int i = 0;
            int mask = 0;
            do {
                // 1-8 биты
                if (mask % 2 == 0) {
                    i = fin.read();
                }
                // 9-16 биты
                if (mask % 2 != 0) {
                    int j = fin.read();
                    System.out.printf("Получили i = %d, j = %d \n",i,j);
                    j = j << 8;
                    char res = (char) (i | j);
                    System.out.printf("Символ = %s, номер символа %d \n",res,(int)res);
                }
                mask++;
            } while (i != -1);




        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла.");
        }
        // Закрыть файл
        try {
            fin.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла");
        }
    }
}














