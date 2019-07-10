package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 7.0
 * @since 10.07.2019
 */
public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

    private final Store store = DbStore.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    private boolean validateUser(User user) {
        boolean result = false;
        if (user.getId() != null && this.store.findById(user) != null) {
            result = true;
        }
        return result;
    }

    @Override
    public User add(User user) {
        User result = null;
        if (!this.validateUser(user)) {
            result = this.store.add(user);
        }
        return result;
    }

    @Override
    public User update(User user) {
        User result = null;
        if (this.validateUser(user)) {
            this.store.update(user);
            result = user;
        }
        return result;
    }

    @Override
    public User delete(User user) {
        User result = null;
        if (this.validateUser(user)) {
            this.store.delete(user);
            result = user;
        }
        return result;
    }

    @Override
    public User findById(User user) {
        return this.store.findById(user);
    }

    @Override
    public List<User> findAll() {
        return store.findAll();
    }

    @Override
    public boolean resetRole(User user) {
        return store.resetRole(user);
    }

    @Override
    public User findByLogin(User user) {
        return store.findByLogin(user);
    }

    @Override
    public boolean isCredential(String login, String password) {
        return store.isCredential(login, password);
    }

    @Override
    public List<String> getCountries() {
        return store.getCountries();
    }

    @Override
    public List<String> getCitiesByCountry(String country) {
        return store.getCitiesByCountry(country);
    }
}