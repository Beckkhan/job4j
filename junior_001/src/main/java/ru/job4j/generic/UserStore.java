package ru.job4j.generic;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.09.2018
 */
public class UserStore<T extends Base> extends AbstractStore<T> {

    public UserStore(SimpleArray<T> database) {
        super(database);
    }
}
