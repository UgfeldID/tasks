package ru.tandemservice.test.task1.data.substrings;

/**
 * Класс пустой подстроки
 */
public class EmptySubstring implements ISubstring {

    @Override
    public int compareTo(ISubstring o) {

        //null всегда меньше пустой подстроки
        if( o == null )
            return this.GREATER;

        //пустые подстроки равны
        if( o.getClass() == this.getClass() ) {
           return EQUAL;
        }

        //все остальные подстроки больше пустой подстроки
        else return this.LESS;
    }
}
