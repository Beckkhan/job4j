package ru.job4j.generic;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.09.2018
 */
public class RoleStore<T extends Base> extends AbstractStore<T> {

    public RoleStore(SimpleArray<T> database) {
        super(database);
    }
}
