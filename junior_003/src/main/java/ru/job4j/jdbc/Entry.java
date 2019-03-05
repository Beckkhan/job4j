package ru.job4j.jdbc;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.03.2019
 */
public class Entry {
    private int field;

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}