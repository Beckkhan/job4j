package ru.job4j.jmm;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 01.12.2018
 */
public class Counter {
    private int counter = 0;

    public void increaseCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}