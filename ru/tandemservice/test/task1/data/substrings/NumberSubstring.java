package ru.tandemservice.test.task1.data.substrings;

/**
 * Класс числовой подстроки (состоящей из цифр)
 */
public class NumberSubstring implements ISubstring {
    private long number = 0;

    public NumberSubstring( String string) {
        this.number = Long.valueOf(string);
    }

    @Override
    public int compareTo(ISubstring o) {

        //null и пустая подстрока всегда меньше числовой подстроки
        if( (o == null) || (o.getClass() == EmptySubstring.class) )
            return this.GREATER;

        // числовые подстроки сравниваются как числа
        if( o.getClass() == this.getClass() ) {
            long anotherNumber = ((NumberSubstring) o).number;
            return Long.compare(number,anotherNumber);
        }

        //все остальные подстроки больше числовой подстроки
        return this.LESS;
    }
}
