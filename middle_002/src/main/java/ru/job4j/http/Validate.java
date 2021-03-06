package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 5.0
 * @since 09.07.2019
 */
public interface Validate {

    User add(User user);

    User update(User user);

    User delete(User user);

    User findById(User user);

    List<User> findAll();

    boolean isCredential(String login, String password);

    User findByLogin(User user);

    boolean resetRole(User user);

    List<String> getCountries();

    List<String> getCitiesByCountry(String country);
}