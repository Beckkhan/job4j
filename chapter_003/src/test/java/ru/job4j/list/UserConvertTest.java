package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 30.01.2019
 */
public class UserConvertTest {
    @Test
    public void whenConvertListUserToMap() {
        UserConvert uc = new UserConvert();
        User first = new User(17, "Feofantii", "Bludogorsk");
        User second = new User(25, "Prokop", "Yablonevka");
        User third = new User(39, "Dobrynya", "Zanikitovka");
        List<User> list = List.of(first, second, third);
        HashMap<Integer, User> result = uc.process(list);
        Map<Integer, User> expect = Map.of(
                first.getId(), first,
                second.getId(), second,
                third.getId(), third
        );
        assertTrue(result.equals(expect));
    }
}