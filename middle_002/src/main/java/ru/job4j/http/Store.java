package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 01.07.2019
 */
public interface Store {

    User add(User user);

    boolean update(User user);

    boolean delete(User user);

    User findById(User user);

    List<User> findAll();

    boolean isCredential(String login, String password);

    User findByLogin(User user);

    boolean resetRole(User user);
}