package ru.job4j.http;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 01.07.2019
 */
public enum Role {
    ADMIN(2), USER(1);

    private final int priority;

    public int getPriority() {
        return priority;
    }

    Role(int priority) {
        this.priority = priority;
    }
}