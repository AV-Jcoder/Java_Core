package com.afoninav.javacore.chapter29;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Пример использования метода map(Function<T,R>)
 * для отображения квадратных корней
 * исходных значений.
 * map() является конвейерным методом.
 */

public class StreamDemo4 {
    public static void main(String[] args) {

        // Создаём экземпляр функционального интерфейса,
        // возвращающего Stream<Double> через метод get()
        Supplier<Stream<Double>> streamOfDoubleSupplier = () -> Stream.of(7.0,18.0,10.0,24.0,17.0,5.0);

        // Получаем экземпляр потока вызвав метод get(),
        // а у потока вызываем метод map()
        // чтобы отобразить квадратные корни исходных значений.
        Stream<Double> stream = streamOfDoubleSupplier.get().map(Math::sqrt);

        // А теперь получаем произведение квадратных корней. Терминирующая операция.
        double productOfSqrtRoot = stream.reduce(1.0, (a, b) -> a * b);
        System.out.println("Произведение квадратных корней равно: " + productOfSqrtRoot);
    }
}
