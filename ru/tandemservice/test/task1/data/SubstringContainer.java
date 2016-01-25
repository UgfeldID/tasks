package ru.tandemservice.test.task1.data;

import ru.tandemservice.test.task1.data.substrings.ISubstring;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс контейнера подстрок
 */
public class SubstringContainer implements Comparable<SubstringContainer>{

    //коллекция подстрок
    private List<ISubstring> substrings = new LinkedList<>();

    @Override
    public int compareTo(SubstringContainer o) {

        if( o == null )
            return ISubstring.GREATER;

        List<ISubstring> anotherSubstrings = o.substrings;
        //т.к. контейнер подстрок может содержать разное количество подстрок
        //запомним минимальный размер коллекции подстрок
        int minSubstringsCount = substrings.size() < anotherSubstrings.size() ?
                substrings.size() : anotherSubstrings.size();


        for (int i = 0; i < minSubstringsCount; i++) {
            int compareResult = substrings.get(i).compareTo( anotherSubstrings.get(i) );
            //если на текущей интерации подстроки не равны, то возвращаем результат сранения
            //если они по-прежнему равны, продолжается проход по циклу
            if( compareResult != ISubstring.EQUAL )
                return compareResult;
        }

        //если метод до сих пор не вернул результат, значит все рассмотренные подстроки равны
        //если количество подстрок одинаково, то рассмотрены все подстроки и контейнеры подстрок равны
        if( substrings.size() == anotherSubstrings.size() )
            return ISubstring.EQUAL;

        //если количество подстрок неодинаково, то рассмотрены не все подстроки и больше тот контейнер,
        //который содержит большее количество подстрок
        return substrings.size() < anotherSubstrings.size() ? ISubstring.LESS : ISubstring.GREATER;
    }

    /**
     * Получить список подстрок
     * @return список подстрок
     */
    public List<ISubstring> getSubstrings() {
        return substrings;
    }

    /**
     * Установить список подстрок
     * @param substrings список подстрок
     */
    public void setSubstrings(List<ISubstring> substrings) {
        this.substrings = substrings;
    }
}
