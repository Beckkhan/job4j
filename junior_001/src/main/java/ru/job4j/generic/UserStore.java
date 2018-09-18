package ru.job4j.generic;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.09.2018
 */
public class UserStore extends AbstractStore<User> {

    public UserStore(SimpleArray<User> database) {
        super(database);
    }
}
