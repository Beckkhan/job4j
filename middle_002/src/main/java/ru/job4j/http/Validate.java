package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 20.06.2019
 */
public interface Validate {

    String add(User user);

    String update(User user);

    String delete(User user);

    User findById(User user);

    List<User> findAll();
}