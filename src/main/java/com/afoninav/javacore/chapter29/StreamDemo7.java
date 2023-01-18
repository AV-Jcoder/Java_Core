package com.afoninav.javacore.chapter29;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Пример использования метода collect() для
 * создания списка типа List
 * и множества типа Set
 * из потока объектов типа Stream.
 */

public class StreamDemo7 {
    public static void main(String[] args) {

        // Создать список имён, номеров телефонов, и адресов электронный почты.
        Supplier<Stream<NamePhoneEmail2>> supplier = () -> Stream.of(
                new NamePhoneEmail2("Любовь", "888-88-88", "lubov.mail.org"),
                new NamePhoneEmail2("Надежа", "939-39-99", "nadezda.mail.org"),
                new NamePhoneEmail2("Вера", "242-22-42", "vera.mail.org")
        );

        // Отобразить только Имена и Телефоны на новый поток данных
        Stream<NamePhone2> stream = supplier.get().map((var) -> new NamePhone2(var.name, var.phone));

        // Создать коллекцию из стрима
        List<NamePhone2> list = stream.collect(Collectors.toList());

        // Вывести на экран коллекцию
        System.out.println("Имена и номера телефонов в коллекции типа List:");
        for (NamePhone2 obj : list) {
            System.out.println(obj.name + ", " + obj.phone);
        }

        // Получить новый стрим, т.к. первый был употреблён.
        stream = supplier.get().map((var) -> new NamePhone2(var.name, var.phone));

        // Создать множество типа Set из стрима.
        Set<NamePhone2> set = stream.collect(Collectors.toSet());

        // Вывести на экран коллекцию
        System.out.println("\nИмена и номера телефонов в коллекции типа Set:");
        for (NamePhone2 obj : set) {
            System.out.println(obj.name + ", " + obj.phone);
        }

        /*
        Использование перегруженного метода collect:

        <R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> accumulator,
                  BiConsumer<R, R> combiner);
         */

        // Получаем новый стрим и создаём из него коллекцию типа List
        stream = supplier.get().map((var) -> new NamePhone2(var.name, var.phone));
        List<NamePhone2> list2 = stream.collect(() -> new LinkedList<>(),
                                                (a,b) -> a.add(b),
                                                (a,b) -> a.addAll(b));
        System.out.println("\nСписок объектов полученный через перегруженный метод collect():");
        for (NamePhone2 obj : list2) {
            System.out.println(obj.name + ", " + obj.phone);
        }

        // Получаем новый стрим и создаём из него коллекцию типа Set
        stream = supplier.get().map(var -> new NamePhone2(var.name, var.phone));
        Set<NamePhone2> set2 = stream.collect(  HashSet::new,
                                                HashSet::add,
                                                HashSet::addAll);
        System.out.println("\nКоллекция типа Set полученная через перегруженный метод collect():");
        for (NamePhone2 obj :
                set2) {
            System.out.println(obj.name + ", " + obj.phone);
        }
    }
}

class NamePhoneEmail2 {
    String name;
    String phone;
    String email;

    NamePhoneEmail2(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

class NamePhone2 {
    String name;
    String phone;

    NamePhone2(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
