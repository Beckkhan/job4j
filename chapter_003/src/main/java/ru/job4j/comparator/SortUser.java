package ru.job4j.comparator;

import java.util.*;

public class SortUser {

    public Set<User> sort (List<User> users) {
        Set<User> result = new TreeSet<>();
        result.addAll(users);
        return result;
    }
}