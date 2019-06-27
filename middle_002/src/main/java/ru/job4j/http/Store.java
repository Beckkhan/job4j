package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 27.06.2019
 */
public interface Store {

    User add(User user);

    boolean update(User user);

    boolean delete(User user);

    User findById(User user);

    List<User> findAll();
}