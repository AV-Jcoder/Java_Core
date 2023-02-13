package com.afoninav.javacore.chapter28;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Что такое CompletableFuture?
 * CompletableFuture используется для асинхронного программирования в Java.
 * Асинхронное программирование — это средство написания неблокирующего кода путём выполнения задачи в отдельном,
 * отличном от главного, потоке, а также уведомление главного потока о ходе выполнения,
 * завершении или сбое.
 *
 * Таким образом, основной поток не блокируется и не ждёт завершения задачи,
 * а значит может параллельно выполнять и другие задания.
 * Наличие такого рода параллелизма значительно повышает производительность программ.
 *
 * Future vs CompletableFuture
 * CompletableFuture это расширение Future API, представленного в Java 5.
 * Future используется как ссылка на результат асинхронной задачи.
 * В нём есть метод isDone() для проверки, завершилась ли задача или нет,
 * а также метод get() для получения результата после его завершения.
 *
 * Future API был хорошим шагом на пути к асинхронному программированию,
 * но ему не хватало некоторых важных и полезных функций.
 *
 * Ограничения Future
 * 1. Его нельзя завершить вручную.
 * Допустим, вы написали функцию получения актуальной цены продукта из удалённого API.
 * Поскольку этот вызов API занимает много времени,
 * вы запускаете его в отдельном потоке и возвращаете Future из функции.
 *
 * Теперь предположим, что удалённый сервис перестал работать и вы хотите завершить Future вручную,
 * передав актуальную цену продукта из кэша.
 * Сможете ли вы сделать это с Future? Нет!
 *
 * 2. Нельзя выполнять дальнейшие действия над результатом Future без блокирования.
 * Future не уведомляет о своём завершении. В нём есть метод get(), который блокирует поток до тех пор,
 * пока результат не станет доступным.
 * Также в Future нельзя повесить функцию-колбэк, чтобы она срабатывала автоматически,
 * как только станет доступен результат.
 *
 * 3. Невозможно выполнить множество Future один за другим.
 * Бывают случаи, когда требуется выполнить длительную операцию и
 * после её завершения передать результат другой длительной операции и так далее.
 * Такой алгоритм асинхронной работы невозможен при использовании Future.
 *
 * 4. Невозможно объединить несколько Future.
 * Предположим, что у вас есть 10 различных задач во Future, которые вы хотите запустить параллельно,
 * и как только все они завершатся, вызвать некоторую функцию. С Future вы не можете сделать и это.
 *
 * 5. Нет обработки исключений.
 * Future API не имеет механизма обработки исключений.
 * Ого! Так много ограничений? Именно! Поэтому у нас и появился CompletableFuture.
 * С его помощью можно достичь всего вышеперечисленного.
 *
 * CompletableFuture реализует интерфейсы Future и CompletionStage и предоставляет огромный
 * набор удобных методов для создания и объединения нескольких Future.
 * Он также имеет полноценную поддержку обработки исключений.
 */

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

/***********************************************************************************************************************
 1. Создание экземпляра CompletableFuture<>
 ***********************************************************************************************************************/
        System.out.println(" 1. Создание экземпляра CompletableFuture<>");
// 1.1 Создание с помощью конструктора.

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        // Главный поток будет ожидать Future бесконечно долго.
        // Поток, вызвавший метот get() блокируется до его завершения.
//        completableFuture.get();

        // установим результат вручную, а затем вернём его.
        // все клиенты, ожидающие этот Future получат его.
        // все последующие вызовы complete() будут игнорироваться.
        completableFuture.complete("Future result");


//**********************************************************************************************************************
// 1.2 Выполнение асинхронных задач с использованием static runAsync(). Пример фонового выполнения задачи,
//    возвращающей void.

        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Я буду работаать в отдельном потоке а не в главном.");
            }
        });

        try {
            completableFuture2.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // использование лямбды вместо анонимного класса.
        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Я буду работать в отдельном потоке а не в главном");
        });

        try {
            completableFuture3.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


//**********************************************************************************************************************
// 1.3 Выполнение асинхронной задачи и возврат результата с помощью supplyAsync()

        // Анонимный класс
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Результат асинхронной задачи";
            }
        });

        // Блокировка и получение результата Future
        String result = future.get();
        System.out.println(result);

        // Лямбда выражение
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Результат асинхронной задачи через лямбда выражение.";
        });

        // Блокировка и получение результата
        result = future2.get();
        System.out.println(result);

/***********************************************************************************************************************
 2. Заметка о пуле потоков и Executor.

 Вы можете поинтересоваться: хорошо, runAsync() и supplyAsync() выполняются в отдельном потоке,
 но мы ведь нигде не создавали новый поток, верно?

 Верно! CompletableFuture выполняет эти задачи в потоке, полученном из глобального ForkJoinPool.commonPool().
 Также вы можете создать пул потоков и передать его методам runAsync() и supplyAsync(),
 чтобы они выполняли свои задачи в потоке, полученном уже из вашего пула потоков.

 Все методы CompletableFuture API представлены в двух вариантах:
 один принимает Executor в качестве аргумента, а второй нет.

 // Вариации методов runAsync() и supplyAsync()
 static CompletableFuture<Void>  runAsync(Runnable runnable)
 static CompletableFuture<Void>  runAsync(Runnable runnable, Executor executor)
 static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)

 **********************************************************************************************************************/
        System.out.println("2. Заметка о пуле потоков и Executor.");
// 2.1 Создание пула потоков и передача в один из методов
        Executor executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Результат асинхронного выполнения в пуле потоков";
        },executor);

        result = future3.get();
        System.out.println(result);

        ExecutorService es = (ExecutorService) executor;
        es.shutdown();



/***********************************************************************************************************************
 3. Преобразование действий с CompletableFuture

 Метод CompletableFuture.get() блокирующий.
 Поток, который его вызовет  будет ждать, пока Future завершится и вернёт результат.

 Но это же не то, что нам нужно, верно? Для построения асинхронных систем мы должны иметь возможность
 повесить на CompletableFuture колбэк, который автоматически вызовется при завершении Future.

 Так что нам не потребуется ждать результат и внутри функции-колбэка мы сможем написать логику,
 которая отработает после завершения Future.

 Вы можете повесить колбэк на CompletableFuture, используя методы:
 - thenApply()
 - thenAccept()
 - thenRun()
 **********************************************************************************************************************/
        System.out.println("3. Преобразование действий с CompletableFuture");
// 3.1 thenApply()
// Вы можете использовать метод thenApply() для обработки и
// преобразования результата CompletableFuture при его поступлении.
// В качестве аргумента он принимает Function<T, R>.
// Function<T, R> это тоже функциональный интерфейс, представляющий функцию,
// которая принимает аргумент типа T и возвращает результат типа R:

        // 1. Создаём CompletableFuture:
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Anonymous user";
        });

        // 2. Добавляем колбэк к Future, используя thenApply() :
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply((var) -> "Hello " + var);

        // 3. Блокировка и получение результата:
        result = greetingFuture.get();
        System.out.println(result);

// Вы также можете сделать несколько последовательных преобразований,
// используя серию вызовов thenApply().
// Результат одного thenApply() передаётся следующему:

        CompletableFuture<String> welcomeText = CompletableFuture
                .supplyAsync(() -> "Anonymous user")
                .thenApply((name) -> "Hello, " + name)
                .thenApply((greeting) -> greeting+  ". Welcome to multithreading world.");

        result = welcomeText.get();
        System.out.println(result);


//**********************************************************************************************************************
// 3.2 thenAccept() и thenRun()
// Если вы не хотите возвращать результат, а хотите просто выполнить часть кода после завершения Future,
// можете воспользоваться методами thenAccept() и thenRun().
// Эти методы являются потребителями и часто используются в качестве завершающего метода в цепочке.

// CompletableFuture.thenAccept() принимает Consumer<T> и возвращает CompletableFuture<Void>.
// Он имеет доступ к результату CompletableFuture, к которому он прикреплён.

        CompletableFuture<String> name = CompletableFuture.supplyAsync(() -> "Admin");
        CompletableFuture<Void> thenAccept = name.thenAccept((s) -> System.out.println("thenAccept() in action. Your name is " + s));

// В отличие от thenAccept(), thenRun() не имеет доступа к результату Future.
// Он принимает Runnable и возвращает CompletableFuture<Void>:

        CompletableFuture<String> name2 = CompletableFuture.supplyAsync(() -> "User1");
        CompletableFuture<Void> thenRun = name2.thenRun(()-> System.out.println("thenRun() in action!"));

/***********************************************************************************************************************
 4. Заметка об асинхронных колбэках
 ***********************************************************************************************************************/
        System.out.println("4. Заметка об асинхронных колбэках");
// 4.1 Все методы-колбэки в CompletableFuture имеют два асинхронных вида:
/**
 * <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
 * <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 * <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 */
// Эти асинхронные виды колбэков помогут распараллелить задачи, выполнив их в отдельном потоке:
        CompletableFuture<String> result1 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "SuperUser";
                })
                .thenApply((s) -> "Hello " + s);

// В приведенном выше примере задача thenApply() выполняется в том же потоке,
// где и задача supplyAsync(), либо в главном потоке,
// если задача supplyAsync() завершается достаточно быстро (попробуйте удалить вызов sleep() для проверки).


//**********************************************************************************************************************
// 4.2 Чтобы иметь больше контроля над потоком, выполняющим задачу,
// вы можете использовать асинхронные колбэки.
// Если вы используете thenApplyAsync(),
// он будет выполнен в другом потоке,
// полученном из ForkJoinPool.commonPool():
        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(() -> "Строка")
                .thenApplyAsync((s) -> "Обработанная " + s);

//**********************************************************************************************************************
// 4.3 Более того, если вы передадите Executor в thenApplyAsync(),
// задача будет выполнена в потоке,
// полученном из пула потоков Executor.

        CompletableFuture<String> future4 = CompletableFuture
                .supplyAsync(() -> "Строка")
                .thenApplyAsync((s) -> "Обработанная " + s, executor);


/***********************************************************************************************************************
 5. Объединение двух CompletableFuture
 ***********************************************************************************************************************/
        System.out.println("5. Объединение двух CompletableFuture");
// 5.1 Комбинирование двух зависимых задач, с использованием thenCompose():
        System.out.println("5.1 Комбинирование двух зависимых задач, с использованием thenCompose()");
// Предположим, что вы хотите получить информацию
// о пользователе из удалённого сервиса, и, как только информация будет доступна,
// получить кредитный рейтинг пользователя уже из другого сервиса.
// реализации методов getUserDetail() и getCreditRating() ниже.
        CompletableFuture<CompletableFuture<String>> result2 =
                getUsersDetail("1").thenApply((name1) -> getCreditRating(name1));

// В предыдущих примерах Supplier, переданный в thenApply(), возвращал простое значение,
// но в этом случае он возвращает CompletableFuture.
// Следовательно, конечным результатом в приведенном выше примере
// является вложенный CompletableFuture.

        String rating = result2.get().get();
        System.out.println(rating);

// Чтобы избавиться от вложенного Future, используйте метод thenCompose():

        CompletableFuture<String> rating1 =
                getUsersDetail("1").thenCompose(name3 -> getCreditRating(name3));
        String rating2 = rating1.get();
        System.out.println(rating2);

// Правило таково: если функция-колбэк возвращает CompletableFuture,
// а вы хотите простой результат, (а в большинстве случаев именно он вам и нужен),
// тогда используйте thenCompose().


//**********************************************************************************************************************
// 5.2 Комбинирование двух независимых задач, с использованием thenCombine()
        System.out.println("5.2 Комбинирование двух независимых задач, с использованием thenCombine()");
// Если thenCompose() используется для объединения двух задач,
// когда одна зависит от другой, то thenCombine() используется,
// когда вы хотите, чтобы две задачи работали независимо друг от друга
// и по завершению обоих выполнялось какое-нибудь действие.

        CompletableFuture<String> cityFuture = CompletableFuture.supplyAsync(() -> "London");
        CompletableFuture<String> countryFuture = CompletableFuture.supplyAsync(() -> "Great Britain");

        CompletableFuture<String> combine = cityFuture.thenCombine(countryFuture,(city, country) -> {
            return city + " is the capital of the " + country;
        });
        System.out.println(combine.get());

// Колбэк, переданный методу thenCombine(),
// вызовется, когда обе задачи завершатся.


/***********************************************************************************************************************
 6. Объединение нескольких CompletableFuture
 ***********************************************************************************************************************/
        System.out.println("6. Объединение нескольких CompletableFuture");
// Мы использовали thenCompose() и thenCombine(),
// чтобы объединить два CompletableFuture вместе.
// Но что, если вы хотите объединить произвольное количество CompletableFuture?
// Можно воспользоваться следующими методами:
/**
 *     static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
 *     static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
 */

// 6.1 CompletableFuture.allOf()

// CompletableFuture.allOf() используется в тех случаях,
// когда есть список независимых задач, которые вы хотите запустить параллельно,
// а после завершения всех задач выполнить какое-нибудь действие.

// Предположим, вы хотите загрузить содержимое 100 различных веб-страниц.
// Вы можете выполнить эту операцию последовательно, но это займет много времени.
// Поэтому вы написали функцию, которая получает ссылку на веб-страницу и возвращает CompletableFuture,
// то есть загружает контент страницы асинхронно:

// Теперь, когда все веб-страницы загрузились,
// вы хотите подсчитать количество страниц,
// содержащих ключевое слово 'Java'.
// Воспользуемся для этого методом CompletableFuture.allOf():

        // Список страниц.
        List<String> webPageLinks = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            webPageLinks.add("https://www." + i + ".com");
        }

        // Асинхронно загружаем содержимое всех веб страниц.
        List<CompletableFuture<String>> pageContentFutures = webPageLinks
                .stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());


        // Создаём комбинированный Future, используя allOf()
        // объект CompletableFuture<Void> возвращается, если все задачи завершились.
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[0])
        );

// Проблема с CompletableFuture.allOf() заключается в том,
// что он возвращает CompletableFuture<Void>.
// Но мы можем получить результаты всех завершённых CompletableFuture,
// дописав несколько строк кода:

        // Когда все задачи завершены, вызываем future.join(), чтобы получить результаты и собрать их в список
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures.stream()
                    .map(pageContentFuture -> pageContentFuture.join())
                    .collect(Collectors.toList());
        });

// Поскольку мы вызываем future.join(), когда все задачи уже завершены,
// блокировка нигде не происходит :-)
// Метод join() похож на get(). Единственное отличие заключается в том,
// что он бросает unchecked-исключение, если CompletableFuture завершается с ошибкой.

// Давайте теперь подсчитаем количество веб-страниц,
// содержащих ключевое слово Java:
        CompletableFuture<Long> countPageFuture = allPageContentsFuture.thenApply(n -> {
            return n.stream().filter(m -> m.contains("Java")).count();
        });
        System.out.println("Количество веб страниц содержащих слово Java: " + countPageFuture.get());


//**********************************************************************************************************************
// 6.2 CompletableFuture.anyOf()
//
// CompletableFuture.anyOf(), как следует из названия,
// завершается сразу же, как только завершается любой из заданных CompletableFuture.
// Конечным результатом будет результат этого первого завершившегося CompletableFuture.
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Результат future5";
        });

        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Результат future6";
        });

        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Результат future7";
        });

        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future5,future6,future7);
        System.out.println(anyFuture.get());

// В приведенном выше примере anyOfFuture завершается,
// когда завершается любой из трёх CompletableFuture.
// Поскольку в future6 задержка меньше, он завершится первым,
// значит, конечным результатом будет:
// Результат future 6.

// CompletableFuture.anyOf() принимает переменное число аргументов Future
// и возвращает CompletableFuture<Object>.
// Проблема CompletableFuture.anyOf() в том, что если у вас есть задачи,
// которые возвращают результаты разных типов,
// то вы не будете знать тип вашего конечного CompletableFuture.

/***********************************************************************************************************************
 7. Обработка исключений CompletableFuture
 ***********************************************************************************************************************/
        System.out.println("7. Обработка исключений CompletableFuture");
// Мы рассмотрели, как создать, преобразовать и объединить CompletableFuture.
// Теперь давайте разберёмся, что делать, если что-то пошло не так.
// Сперва рассмотрим, как ошибки распространяются в цепочке задач. Например:

        CompletableFuture.supplyAsync(() -> {
            // Код, который может выбросить исключение
            return "Некоторый результат";
        }).thenApply(result3 -> {
            return "Обработанный результат";
        }).thenApply(result4 -> {
            return "Результат дальнейшей обработки";
        }).thenAccept(result5 -> {
            // Какие-то действия с окончательным результатом
        });

// Если в исходной задаче supplyAsync() возникнет ошибка,
// тогда ни одна из последующих задач thenApply() не будет вызвана
// и Future завершится с исключением. Если ошибка возникнет в первом thenApply(),
// то все последующие задачи в цепочке не будут запущены и
// Future всё так же завершится с исключением.

//**********************************************************************************************************************
// 7.1 Обработка исключений с использованием метода exceptionally()

// Метод exceptionally() даёт возможность
// обойти возможные ошибки, если они есть.
// Можно залогировать исключение и вернуть значение по умолчанию.

        Integer age = -1;

        CompletableFuture<String> yearCategoryFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным.");
            } else if (age > 18) {
                return "Взрослый";
            } else {
                return "Ребенок";
            }
        }).exceptionally((ex) -> {
            System.out.println("Исключение перехвачено" + ex.getMessage());
            return "Неизвестно";
        });
        System.out.println("Зрелость: " + yearCategoryFuture.get());

// Обратите внимание, что ошибка не будет
// распространяться далее по цепочке,
// если вы её обработаете.


//**********************************************************************************************************************
// 7.2 Обработка исключений с использованием метода handle()

// Для восстановления после исключений, API
// также предоставляет более общий метод handle().
// Он вызывается независимо от того,
// возникло исключение или нет.

        Integer age2 = -5;

        CompletableFuture<String> yearCategoryFuture2 = CompletableFuture.supplyAsync(() -> {
            if (age2 < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным. age = " + age2);
            } else if (age2 > 18) {
                return "Взрослый";
            } else {
                return "Ребенок";
            }
        }).handle((str,ex) -> {
            if (ex == null) {
                return str;
            } else {
                System.out.println("Исключение перехвачено" + ex.getMessage());
                return "Неизвестная категория";
            }
        });
        System.out.println("Зрелость: " + yearCategoryFuture2.get());

// Если возникает исключение,
// аргумент str будет null,
// если не возникает,
// то ex будет null.

    }

    // реализации методов getUserDetail() и getCreditRating()
    static CompletableFuture<String> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            return userId.equals("1") ? "Ivanov Ivan" : "Empty state";
        });
    }

    static CompletableFuture<String> getCreditRating(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return name.equals("Ivanov Ivan") ? "Brilliant" : "Empty state";
        });
    }

    // метод для "загрузки" веб страницы
    static CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            // Код загрузки и возврата содержимого веб-страницы
            return "web page, that contain text about Java education";
        });
    }
}
