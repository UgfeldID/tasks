package ru.tandemservice.test.task1.data.substrings;

/**
 * Класс символьной подстроки (не пустой подстроки, не содержащей цифры)
 */
public class LetterSubstring implements ISubstring {

    private String string = null;

    public LetterSubstring(String string) {
        this.string = string;
    }

    @Override
    public int compareTo(ISubstring o) {

        // null всегда меньше символьной подстроки
        if( o == null )
            return this.GREATER;

        if( o.getClass() == this.getClass() ) {
            String anotherString = ((LetterSubstring) o).string;
            if ( string == null ) {
                //если обе подстроки состоят из null строк, то они равны
                if ( anotherString == null )
                    return EQUAL;
                //если одна из подстрок состоит из null строки, то эта подстрока меньше другой
                else
                    return this.LESS;
            }
            else {
                //если одна из подстрок состоит из null строки, то эта подстрока меньше другой
                if ( anotherString == null )
                    return this.GREATER;
                //если обе символьные подстроки не состоят из null строк, то они сравниваются как строки
                else return string.compareTo(anotherString);
            }
        }

        //все остальные подстроки меньше символьной подстроки
        else return this.GREATER;
    }
}
