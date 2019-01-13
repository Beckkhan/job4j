package ru.job4j.nonblockingalgoritm;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 13.01.2019
 */
public class Base {
    private int id;
    private int version;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void modify() {
        this.version++;
    }
}