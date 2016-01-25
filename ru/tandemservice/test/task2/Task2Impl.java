package ru.tandemservice.test.task2;

import ru.tandemservice.test.task1.Task1Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <h1>Задание №2</h1>
 * Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>Помимо качества кода, мы будем обращать внимание на оптимальность предложенного алгоритма по времени работы
 * с учетом скорости выполнения операции присвоения номера:
 * большим плюсом (хотя это и не обязательно) будет оценка числа операций, доказательство оптимальности
 * или указание области, в которой алгоритм будет оптимальным.</p>
 */
public class Task2Impl implements IElementNumberAssigner {

    public static volatile IElementNumberAssigner INSTANCE;

    private Task2Impl(){}

    /**
     * Получить экземпляр класса
     * @return  экземпляр класса
     */
    public static IElementNumberAssigner getInstance() {
        if( INSTANCE == null ){
            synchronized ( Task1Impl.class ){
                if( INSTANCE == null ){
                    INSTANCE = new Task2Impl();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void assignNumbers(final List<IElement> elements) {

        ExecutorService executor = Executors.newFixedThreadPool( determineThreadsCount() );
        List<Callable<Object>> tasks = new ArrayList<>();
        //формирование задач, выполняющихся параллельно
        for ( int i =0; i<elements.size(); i++) {
            final int position = i;
            Runnable numberSetter = () -> {
                IElement element = elements.get(position);
                if( element.getNumber() != position)
                    element.setupNumber(position);
            };

            tasks.add(Executors.callable(numberSetter));
        }

        //выполнение задач параллельно
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //завершение работы, при котором ранее переданные задачи исполняются,
        // но новые задачи не принимаются
        executor.shutdown();
    }

    /**
     * Определить количество потоков
     * @return количество потоков
     */
    private int determineThreadsCount() {

        //в зависимости от характера операций, выполняемых в реализациях методов setupNumber(int number)
        //интерфейса IElement оптимальное количество потоков может меняться
        //если в методе выполняются только вычислительные задачи, то оптимальное количество потоков = количество потоков доступных JVM
        //если поток, выполняющий метод находится какое-то время в состоянии ожидания, то  оптимальное количество потоков > количество потоков доступных JVM

        //чем больше потоков, тем больше требуется памяти
        //также узким местом может являться скорость чтения/записи на диск,
        //поэтому оптимальное количество потоков выялвяется на нагрузочных тестах

        //количество потоков доступных JVM
        return Runtime.getRuntime().availableProcessors();
    }

}
