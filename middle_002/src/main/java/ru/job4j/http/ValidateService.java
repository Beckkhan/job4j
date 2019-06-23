package ru.job4j.http;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 23.06.2019
 */
public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

    private final Store store = MemoryStore.getInstance();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
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
    public String add(User user) {
        String result = "This user already exists";
        return this.validateUser(user) ? result : String.format("Added user with ID %s.", store.add(user));
    }

    @Override
    public String update(User user) {
        String result = "No such user was found.";
        if (this.validateUser(user)) {
            this.store.update(user);
            result = "The user data is updated";
        }
        return result;
    }

    @Override
    public String delete(User user) {
        String result = "No such user was found.";
        if (this.validateUser(user)) {
            this.store.delete(user);
            result = String.format("The User with the ID%s was successfully removed.", user.getId());
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
}