package ru.job4j.http;

import java.util.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 09.07.2019
 */
public class ValidateStub implements Validate {

    private final Map<Integer, User> store = new HashMap<>();

    /**
     * Map in which the keys are the city, and the values are the country of location of the city.
     */
    private final Map<String, String> location = new HashMap<>();

    private int ids = 0;

    @Override
    public User add(User user) {
        user.setId(String.valueOf(this.ids++));
        this.store.put(Integer.parseInt(user.getId()), user);
        this.location.putIfAbsent(user.getCity(), user.getCountry());
        return store.get(ids);
    }

    @Override
    public User update(User user) {
        User result = null;
        this.location.putIfAbsent(user.getCity(), user.getCountry());
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
    public User findById(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store.values());
    }

    @Override
    public boolean isCredential(String login, String password) {
        return false;
    }

    @Override
    public User findByLogin(User user) {
        return null;
    }

    @Override
    public boolean resetRole(User user) {
        return false;
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