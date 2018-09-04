package ru.job4j.transfer;

/**
 * Класс пользователя User
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1
 * @since 04.09.2018
 * */
public class User {

    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }
    public String getName() {
        return this.name;
    }

    public String getPassport() {
        return this.passport;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        User user = (User) o;
        if (user.name != null
                && user.passport != null
                && user.name.equals(this.name)
                && user.passport.equals(this.passport)) {
            result = true;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result += passport != null ? passport.hashCode() : 0;
        return result;
    }
}