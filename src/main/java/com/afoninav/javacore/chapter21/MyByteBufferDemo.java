package com.afoninav.javacore.chapter21;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Пример использования буфера ByteBuffer из пакета java.nio
 *
 *  position - текущая позиция в массиве для чтения/записи.
 *  limit - текущий предел для чтения/записи, который можно двигать в зависимости от задачи.
 *  capacity - длина массива.
 *
 * allocate() - create буффер
 * wrap() - обенуть массив байт в буфер.
 * get() - считать из буффера
 * put() - записать в буфер
 * проблема в том, что методы get() и put() берут данные из ячейки byte[position], поэтому после чтения в буфер данных,
 * если мы хотим записать данные куда-либо, из буфера в файл, то указатель position и limit нужно передвинуть.
 *
 * clear() - сброс меток: position на первый элемент, limit на последний(capacity)
 * flip () - установить limit на текущий указатель position, переместив после этого position в начало.
 *
 * Если в канале, из которого мы читаем, данные уже появились, а канал, в который мы пишем принял только часть данных,
 * но пока не может принять остальные данные, то может возникнуть ситуация простоя программы.
 * Чтобы её не было мы можем считать данные из канала в буфер, предварительно его подготовив.
 * Все данные которые в данный момент не могут быть записаны сдвигаются в начало.
 *
 * compact() - сдвигает данные от position до limit в начало, перемещая указатель position за эти данные,
 * а limit в самый конец.
 * Этот метод очень удобен, и его вызов имеет смысл перед тем как начать считывать данные в буфер из канала, только
 * если перед этим этим была сделана частичная запись из буфера в какой-либо канал.
 *
 * Но стоит помнить, что стоимость такой операции перемещения может быть O(n) в худшем случае.
 *
 */
class MyByteBufferDemo {
    public static void main(String[] args) throws IOException {

        // В файле строка "Hello"
        FileChannel inChannel = new FileInputStream("/tmp/file1.txt").getChannel();
        FileChannel outChannel = new FileOutputStream("/tmp/file2.txt").getChannel();
        ByteBuffer buf = ByteBuffer.allocate(25);
        System.out.println("1) Буфер после инициализации:");
        System.out.println(buf); // 0 25 25 - после инициализации

        // Читаем в буф
        inChannel.read(buf);
        System.out.println("2) Буфер после чтения из канала:");
        System.out.println(buf); // 5 25 25 - после чтения в буф


        // Пишем из буфа в канал, готовим позиции
        // Вместо одного метода flip() можно вызвать два метода ниже.
        buf.limit(buf.position());
        buf.position(0);
        System.out.println("3) Буфер перед записью в файл (канал):");
        System.out.println(buf);

        byte[] byte1 = new byte[2];
        byte1[0] = buf.get(); // H
        byte1[1] = buf.get(); // e

        ByteBuffer bb = ByteBuffer.wrap(byte1);
        outChannel.write(bb);
        System.out.println("4) Буфер после записи в канал (записаны 2 символа):"); // Записаны "He"
        System.out.println(buf);

        // Снова читаем в буф, т.к. позиции pos=2 lim=5, это означает, что между 2 и 5 есть данные,
        // которые еще не были записаны в другой канал (3 символа "llo").
        // Эти данные нужно сдвинуть в начало, и только потом считывать в буфер из другого канала,
        // в позицию сразу за этими данными.
        buf.compact(); // теперь позиции 3 25
        System.out.println("5) Буфер после вызова compact(), готов для чтения из канала:");
        System.out.println(buf);
        inChannel.position(0);
        inChannel.read(buf);
        System.out.println("6) Буфер после чтения из канала (прочитана строка целиком \"Hello\"):");
        System.out.println(buf); // после чтения позиции 8 25

        // Записываем данные в канал
        // Готовим позиции
        buf.flip();
        System.out.println("7) Буфер после метода flip(), готов для записи в канал:");
        System.out.println(buf); // должно быть 0 8 25
        outChannel.write(buf);
        System.out.println("8) Буфер после записи в канал:");
        System.out.println(buf);
        System.out.println("9) Дальнейший вызов get() или put() выбросит исключение:") ;
        System.out.println(buf.put((byte)1));
    }
}
