package com.afoninav.javacore.chapter29;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Пример использования метода map()
 * для создания потока, содержащего только
 * избранные поля от исходного потока.
 *
 * В примере класс NamePhoneEmail содержит
 * как ни странно Имя, Телефон и Е-маил.
 *
 * А класс NamePhone содержит информацию
 * только о Имени и Телефоне.
 *
 * При вызове метода- R apply(T t) объекта- Function<T,R> mapper -
 * из Stream<T>.map() мы принимаем в качестве параметра объект типа NamePhoneEmail,
 * считываем с него данные в новый объект типа NamePhone и
 * возвращаем этот новый объект в новый стрим типа Stream<R>
 *
 */

public class StreamDemo5 {
    public static void main(String[] args) {

        // Список имён, телефонов и е-маилов.
        Supplier<List<NamePhoneEmail>> supplier = () -> List.of(
                new NamePhoneEmail("Любовь", "888-88-88", "Lubov@gmail.com"),
                new NamePhoneEmail("Надежда", "933-93-93", "nadin@mail.com"),
                new NamePhoneEmail("Вера", "555-55-45", "vera@mailcom")
        );
        List<NamePhoneEmail> list = supplier.get();

        System.out.println("Исходные элементы из списка: " );
        list.stream().forEachOrdered((var) -> {
            System.out.println(var.name + ", " + var.phone + ", " + var.email);
        });
        System.out.println("");

        // Отобразить на новый поток данных
        // только имена и номера телефонов
        Stream<NamePhone> nameAndPhone = list.stream().map(var -> new NamePhone(var.name, var.phone));
        System.out.println("Список имён и телефонов: ");
        nameAndPhone.forEach((var)-> System.out.println(var.name + ", " + var.phone));
        System.out.println("");

        // Объединение нескольких промежуточных оперций в конвейер.
        // Сперва вызывается метод filter(), отсеивающий все имена кроме "Любовь"
        // а затем отображается методом map() исходный стрим в новый.
        NamePhone namePhoneObject = list.stream()
                .filter((var)-> var.name.equals("Любовь"))
                .map((var) -> new NamePhone(var.name, var.phone))
                .findAny().get();
        System.out.println("Результат конвейерных операций: \n"
                + namePhoneObject.name + ", " + namePhoneObject.phone);
    }
}

class NamePhoneEmail {
    String name;
    String phone;
    String email;

    NamePhoneEmail(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

class NamePhone {
    String name;
    String phone;

    NamePhone(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}