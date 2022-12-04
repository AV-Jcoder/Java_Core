package com.afoninav.javacore.chapter15;

/**
 * Пример применения ссылки на обобщенный метод,
 * объявленный в необобщенном классе.
 */

public class GenericMethodRefDemo {
    // В качестве первого параметра этого метода указывается
    // функциональный интерфейс MyFunc. В качестве двух других
    // массив и значение, причём оба одинакового типа - Т.
    static <T> int myOp(MyFunc2<T> mf, T[] vars, T v) {
        return mf.func(vars, v);
    }

    public static void main(String[] args) {
        Integer[] vals = {1,2,3,4,2,3,4,4,5};
        String[] strs = {"One","Two","Three","Two"};
        int count;

        count = myOp(MyArrayOps::<Integer>countMatching, vals, 4);
        System.out.printf("Массив Integer[] vals содержит %d числа 4. \n", count);

        count = myOp(MyArrayOps::<String>countMatching, strs, "Two");
        System.out.printf("Массив String[] strs содержит %d строки \"Two\" \n", count);
    }

}

// В этом классе определяется метод countMatching(), возвращающий количество элементов в массиве,
// равных указанному значению.
// Нужно обратить внимание на то, что метод countMatching() обобщён, а
// класс MyArrayOps не обобщён.
class MyArrayOps {
    static <T> int countMatching(T[] vars, T var) {
        int count = 0;
        for (int i = 0; i < vars.length; i++) {
            if (vars[i] == var) {
                count++;
            }
        }
        return count;
    }
}

// Функциональный интерфейс для обработки массива значений и
// возврата целочисленного результата
@FunctionalInterface
interface MyFunc2<T> {
    int func(T[] vars, T var);
}