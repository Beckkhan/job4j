package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 20.06.2019
 */
public interface Store {

    String add(User user);

    boolean update(User user);

    boolean delete(User user);

    User findById(User user);

    List<User> findAll();
}
