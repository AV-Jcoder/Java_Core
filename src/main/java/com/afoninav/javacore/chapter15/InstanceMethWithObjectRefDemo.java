package com.afoninav.javacore.chapter15;

/**
 * Пример использования ссылки на метод экземпляра с разными объектами.
 * Ссылка на метод экземпляра указывается через имя класса.
 *
 * Еще одна особенность в том, что метод функционального интерфейса имеет 2 параметра, а передаваемый метод только 1
 * В этом случае объект, вызывающий метод, приводится к первому аргументу метода функционального интерфеса, а
 * а ко второму параметру функционального интерфейса приводится первый аргумент метода, который вызывает объект.
 * Сложно.
 */

public class InstanceMethWithObjectRefDemo {
    // Метод, возвращающий количество экземпляров объекта,
    // найденных по критериям, задаваемым параметром
    // функционального интерфейса MyFunc
    static <T> int counter(T[] vals, MyFunc1<T> f, T v) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            if (f.myFunc(vals[i], v)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int count;
        // Создать массив объектов HighTemp.
        HighTemp[] weekDayHighs = {new HighTemp(89), new HighTemp(82),
                                    new HighTemp(90), new HighTemp(89),
                                    new HighTemp(89), new HighTemp(91),
                                    new HighTemp(84), new HighTemp(83)};

        // Использовать метод counter() вместе с массивами объеков типа HighTemp.
        // Нужно обратить внимание на то, что ссылка на метод экземпляра sameTemp()
        // передаётся в качестве второго параметра
        count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));
        System.out.println("Дней, когда температура была 89: " + count);

        // А теперь использовать вместе с данным
        // методом еще один массивобъектов типа HighTemp.
        HighTemp[] weekDayHighs2 = {new HighTemp(32), new HighTemp(12),
                                    new HighTemp(24), new HighTemp(19),
                                    new HighTemp(18), new HighTemp(12),
                                    new HighTemp(-1), new HighTemp(13)};
        count = counter(weekDayHighs2, HighTemp::sameTemp ,new HighTemp(12));
        System.out.println("Дней, когда температура была 12: " + count);

        // А теперь воспользуемся методом lessThen(), чтобы выяснить
        // сколько дней температура была меньше заданной.
        count = counter(weekDayHighs, HighTemp::lessThenTemp, new HighTemp(89));
        System.out.println("Дней, когда температура была меньше 89: " + count);

        // Аналогично, только пример со вторым массивом
        count = counter(weekDayHighs2, HighTemp::lessThenTemp, new HighTemp(19));
        System.out.println("Дней, когда температура была меньше 19: " + count);


    }
}

// Класс для хранения максимальной температуры за день
class HighTemp {
    private int temp;

    HighTemp(int temp){
        this.temp = temp;
    }

    // Метод вернёт true, если вызывающий объект имеет такую же температуру как и объект obj
    boolean sameTemp(HighTemp obj){
        return this.temp == obj.temp;
    }

    // Метод вернёт true, если вызывающий объект иммет меньшую температуру, чем у объекта obj
    boolean lessThenTemp(HighTemp obj){
        return this.temp < obj.temp;
    }
}

// Функциональный интерфейс с методом, принимающим два ссылочных
// аргумента и возвращающим логическое ззначение
@FunctionalInterface
interface MyFunc1<T> {
    boolean myFunc(T v1, T v2);
}
