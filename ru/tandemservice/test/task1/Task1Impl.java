package ru.tandemservice.test.task1;

import java.util.*;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

    public static volatile IStringRowsListSorter INSTANCE;

    private Task1Impl(){}

    /**
     * Получить экземпляр класса
     * @return  экземпляр класса
     */
    public static IStringRowsListSorter getInstance() {
        if( INSTANCE == null ){
            synchronized ( Task1Impl.class ){
                if( INSTANCE == null ){
                    INSTANCE = new Task1Impl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
        //в основе этой сортировки лежит сортировка слиянием, она является устойчивой
        Collections.sort(rows, new StringArrayComparator(columnIndex));
    }
}
