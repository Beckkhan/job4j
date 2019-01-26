package ru.job4j.comparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 02.09.2018
 */
public class SortUser {

    public Set<User> sort(List<User> users) {
        return users.stream().sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.toCollection(TreeSet::new));
        /*Set<User> result = new TreeSet<>();
        result.addAll(users);
        return result;*/
    }

    static class NameLenghtCompare implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public List<User> sortNameLength(List<User> users, NameLenghtCompare nameLenghtComparator) {
        return users.stream().sorted(nameLenghtComparator).collect(Collectors.toList());
        /*Collections.sort(users, nameLenghtComparator);
        return users;*/
    }

    static class AllFieldsCompare implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            int result = o1.getName().compareTo(o2.getName());
            if (result == 0) {
                result = Integer.compare(o1.getAge(), o2.getAge());
            }
            return result;
        }
    }

    public List<User> sortByAllFields(List<User> users, AllFieldsCompare allFieldsComparator) {
        return users.stream().sorted(allFieldsComparator).collect(Collectors.toList());
        /*Collections.sort(users, allFieldsComparator);
        return users;*/
    }
}