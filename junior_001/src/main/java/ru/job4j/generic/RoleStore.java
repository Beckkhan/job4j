package ru.job4j.generic;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.09.2018
 */
public class RoleStore extends AbstractStore<Role> {

    public RoleStore(SimpleArray<Role> database) {
        super(database);
    }
}
