package ru.job4j.http;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 20.06.2019
 */
public interface Validate {

    String add(User user);

    String update(User user);

    String delete(User user);

    String findById(User user);

    String findAll();
}
