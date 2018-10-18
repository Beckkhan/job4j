package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 18.10.2018
 */
public class UserTest {
    Calendar birthday = new GregorianCalendar(2018, 10, 18);
    private User first = new User("Kolobok", 3, birthday);
    private User second = new User("Kolobok", 3, birthday);

    @Test
    public void whenCreateTwoEqualUsersAndAddToHashSetThenCheck() {
        Set<User> users = new HashSet<>();
        users.add(first);
        users.add(second);
        System.out.println(users);
        assertThat(users.size(), is(1));
    }
}