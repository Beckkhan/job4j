package ru.job4j.http;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 20.06.2019
 */
public class MemoryStore implements Store {

    private final static MemoryStore INSTANCE = new MemoryStore();

    private final AtomicInteger idCounter = new AtomicInteger();

    private final ConcurrentMap<String, User> users = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    /**
     * This is the a method of adding a user to the store.
     * @param user
     * @return new User Id
     */
    @Override
    public String add(User user) {
        user.setId(String.valueOf(idCounter.incrementAndGet()));
        user.setCreateDate(LocalDate.now());
        users.put(user.getId(), user);
        return String.valueOf(user.getId());
    }

    /**
     * This is a method of updating user data, if it exists in the store
     * @param user
     * @return the result of the operation
     */
    @Override
    public boolean update(User user) {
        user.setCreateDate(LocalDate.now());
        return user.getId() != null && users.replace(user.getId(), user) != null;
    }

    /**
     * This is a method to remove a user from the store.
     * @param user
     * @return the result of the operation
     */
    @Override
    public boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    /**
     * This method searches for the user by ID number.
     * @param user
     * @return user found
     */
    @Override
    public User findById(User user) {
        return users.get(user.getId());
    }

    /**
     * This method returns information about all users in the store.
     * @return store
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}