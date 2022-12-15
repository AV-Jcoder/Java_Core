package com.afoninav.javacore.chapter20;

import java.io.*;

/**
 * Пример применения сериализации и десериализации с помощью
 * классов ObjectOutputStream и
 * ObjectInputStream.
 */

public class SerializationDemo {
    public static void main(String[] args) {

        // Производим серииализацию объекта
        try (ObjectOutputStream objectOutput =
                     new ObjectOutputStream(new FileOutputStream("/tmp/file1.txt"))) {
            MyClass object1 = new MyClass("Hello", -7, 2.7e10);
            System.out.println("Object1: " + object1);
            objectOutput.writeObject(object1);
        } catch (IOException e) {
            System.out.println("Input-Output error: " + e);
        }

        // Производим десериализацию объекта
        try (ObjectInputStream objectInput =
                     new ObjectInputStream(new FileInputStream("/tmp/file1.txt"))) {
            MyClass object2;
            Object deserializableObject = objectInput.readObject();
            if (deserializableObject instanceof MyClass) {
                object2 = (MyClass) deserializableObject;
                System.out.println("Object2: " + object2);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Input-Output error: " + e);
        }
    }
}

class MyClass implements Serializable {
    String s;
    int i;
    double d;

    public MyClass(String s, int i, double d) {
        this.s = s;
        this.i = i;
        this.d = d;
    }

    @Override
    public String toString() {
        return "s=" + s + "; i=" + i + "; d=" +d;
    }
}