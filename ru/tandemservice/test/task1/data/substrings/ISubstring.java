package ru.tandemservice.test.task1.data.substrings;

/**
 * Интерфейс подстроки
 */
public interface ISubstring extends Comparable<ISubstring> {
    /*
    Тип подстроки
     */
    enum Type {
        LETTER,
        NUMBER,
        EMPTY
    }

    //возможные результаты сравнения подстрок
    int GREATER = 1;
    int EQUAL = 0;
    int LESS = -1;
}
