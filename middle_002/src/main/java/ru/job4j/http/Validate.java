package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 01.07.2019
 */
public interface Validate {

    String add(User user);

    String update(User user);

    String delete(User user);

    User findById(User user);

    List<User> findAll();

    boolean isCredential(String login, String password);

    User findByLogin(User user);

    boolean resetRole(User user);
}