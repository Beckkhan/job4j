package ru.job4j.http;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 4.0
 * @since 09.07.2019
 */
public class MemoryStore implements Store {

    private final static MemoryStore INSTANCE = new MemoryStore();

    private final AtomicInteger idCounter = new AtomicInteger();

    private final ConcurrentMap<String, User> users = new ConcurrentHashMap<>();

    /**
     * Map in which the keys are the city, and the values are the country of location of the city.
     */
    private final Map<String, String> location = new HashMap<>();

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
    public User add(User user) {
        user.setId(String.valueOf(idCounter.incrementAndGet()));
        user.setCreateDate(LocalDate.now());
        users.put(user.getId(), user);
        this.location.putIfAbsent(user.getCity(), user.getCountry());
        return user;
    }

    /**
     * This is a method of updating user data, if it exists in the store
     * @param user
     * @return the result of the operation
     */
    @Override
    public boolean update(User user) {
        user.setCreateDate(LocalDate.now());
        this.location.putIfAbsent(user.getCity(), user.getCountry());
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

    @Override
    public User findByLogin(User user) {
        User result = null;
        for (User next : this.findAll()) {
            if (next.getLogin().equals(user.getLogin())) {
                result = next;
            }
        }
        return result;
    }

    @Override
    public boolean isCredential(String login, String password) {
        boolean result = false;
        for (User next : findAll()) {
            if (login.equals(next.getLogin()) && password.equals(next.getPassword())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean resetRole(User user) {
        boolean result = false;
        User reset = users.get(user.getId());
        if (reset != null) {
            reset.setRole(user.getRole());
            result = true;
        }
        return result;
    }

    @Override
    public List<String> getCountries() {
        Set<String> states = new TreeSet<>();
        for (String country : this.location.values()) {
            states.add(country);
        }
        return new ArrayList<>(states);
    }

    @Override
    public List<String> getCitiesByCountry(String country) {
        Set<String> places = new TreeSet<>();
        for (String city : this.location.keySet()) {
            if (this.location.get(city).equals(country)) {
                places.add(city);
            }
        }
        return new ArrayList<>(places);
    }
}