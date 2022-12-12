package com.afoninav.javacore.chapter20;

import java.io.*;

/**
 * Пример применения классов
 * DataInputStream и DataOutPutStream
 *
 *
 */

public class DataIODemo {
    public static void main(String[] args) {

        // выводим данные в файл
        try (DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("/tmp/2.txt"))) {
            dataOut.writeDouble(98.6);
            dataOut.writeInt(1000);
            dataOut.writeBoolean(true);
        } catch(FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }

        // теперь вводим данные из файла
        try(DataInputStream dataIn = new DataInputStream(new FileInputStream("/tmp/2.txt"))) {
            double d = dataIn.readDouble();
            int i = dataIn.readInt();
            boolean b = dataIn.readBoolean();
            System.out.printf("%f %d %b",d,i,b);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }
    }
}
