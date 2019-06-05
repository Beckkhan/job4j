package ru.job4j.pool;
/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.06.2019
 */
public class User {

    private String name;
    private String mail;

    public User(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return mail;
    }
}