package ru.job4j.controltask;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 06.04.2019
 */
public interface PlayingSide {

    Cell playerMove();

    void loadValue(String cellValue);

    String showName();
}