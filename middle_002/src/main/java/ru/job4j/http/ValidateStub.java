package ru.job4j.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 02.07.2019
 */
public class ValidateStub implements Validate {

    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public User add(User user) {
        user.setId(String.valueOf(this.ids++));
        this.store.put(Integer.parseInt(user.getId()), user);
        return store.get(ids);
    }

    @Override
    public User update(User user) {
        User result = null;
        if (store.containsKey(Integer.parseInt(user.getId()))) {
            store.replace(Integer.parseInt(user.getId()), user);
            result = user;
        }
        return result;
    }

    @Override
    public User delete(User user) {
        return this.store.remove(Integer.parseInt(user.getId()));
    }

    @Override
    public User findById( User user ) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store.values());
    }

    @Override
    public boolean isCredential( String login, String password ) {
        return false;
    }

    @Override
    public User findByLogin( User user ) {
        return null;
    }

    @Override
    public boolean resetRole( User user ) {
        return false;
    }
}