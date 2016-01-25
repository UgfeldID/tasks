package ru.tandemservice.test.task1;

import ru.tandemservice.test.task1.data.SubstringContainer;
import ru.tandemservice.test.task1.data.StringParser;

import java.util.*;

/**
 * Компаратор массива строк
 */
public class StringArrayComparator implements Comparator<String[]> {

    //индекс столбца в массиве, по которому сраниваются массивы строк
    private int index;

    /**
     * Конструктор с параметром
     * @param index индекс столбца в массиве, по которому сраниваются массивы строк
     */
    public StringArrayComparator(int index) {
        this.index = index;
    }

    @Override
    public int compare(String[] o1, String[] o2) {
        SubstringContainer firstSubstringContainer  = StringParser.parse(o1[index]);
        SubstringContainer secondSubstringContainer = StringParser.parse(o2[index]);

        return firstSubstringContainer.compareTo(secondSubstringContainer);
    }
}
