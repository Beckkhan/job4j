package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 21.10.2018
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && children == user.children
                && Objects.equals(birthday, user.birthday);
    }*/
}