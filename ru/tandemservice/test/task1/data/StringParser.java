package ru.tandemservice.test.task1.data;

import ru.tandemservice.test.task1.data.substrings.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для разделения строки
 */
public class StringParser {

    /**
     * Разбивает строку на подстроки и возвращает контейнер подстрок
     * @param sourceString исходная строка
     * @return  контейнер подстрок
     */
    public static SubstringContainer parse(String sourceString){
        SubstringContainer substringContainer = new SubstringContainer();

        if( sourceString == null )
            return substringContainer;

        List<ISubstring> substrings = new ArrayList<>();

        if( sourceString.isEmpty() ){
            substrings.add( createSubstring("", ISubstring.Type.EMPTY) );
            substringContainer.setSubstrings(substrings);
            return substringContainer;
        }

        //запоминаем начальный индекс подстроки в разбиваемой строке и тип подстроки
        int startCharNumber = 0;
        ISubstring.Type substringType = determineCharType( sourceString.charAt(0));

        for (int lastCharNumber = 0; lastCharNumber < sourceString.length(); lastCharNumber++) {

            //если тип текущего символа не совпадает с типом выделяемой подстроки,
            //значит текущий символ принадлежит другой подстроке
            if( substringType != determineCharType( sourceString.charAt(lastCharNumber) ) ){
                addNewSubstringToList( substrings, sourceString.substring(startCharNumber,lastCharNumber),
                        substringType);
                startCharNumber = lastCharNumber ;
                substringType = determineCharType( sourceString.charAt(startCharNumber) );
            }
        }

        addNewSubstringToList( substrings, sourceString.substring(startCharNumber,sourceString.length()),
                substringType);

        substringContainer.setSubstrings(substrings);
        return substringContainer;
    }

    /**
     * Создает подстроку и добавляет ее в переданный список подстрок
     * @param substrings список подстрок, в который добавляется новая подстрока
     * @param substringContent строка для создания подстроки
     * @param substringType тип создаваемой подстроки
     */
    private static void addNewSubstringToList(List<ISubstring> substrings, String substringContent, ISubstring.Type substringType) {
        ISubstring stringPart = createSubstring( substringContent, substringType );
        substrings.add(stringPart);
    }

    /**
     * Создает подстроку
     * @param string строка для создания подстроки
     * @param stringType тип создаваемой подстроки
     * @return подстрока
     */
    private static ISubstring createSubstring(String string, ISubstring.Type stringType){
        switch (stringType) {
            case LETTER:
                return new LetterSubstring(string);
            case NUMBER:
                return new NumberSubstring(string);
            case EMPTY:
                return new EmptySubstring();
        }
        return new EmptySubstring();
    }

    /**
     * Определяет тип переданного символа
     * @param ch символ
     * @return тип символа
     */
    private static ISubstring.Type determineCharType(char ch) {
        if( ch == ' ') return ISubstring.Type.EMPTY;
        if( Character.isDigit(ch) )
            return ISubstring.Type.NUMBER;
        else return ISubstring.Type.LETTER;
    }
}
