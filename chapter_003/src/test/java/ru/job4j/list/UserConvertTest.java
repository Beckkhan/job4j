package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UserConvertTest {
    @Test
    public void whenConvertListUserToMap() {
        UserConvert uc = new UserConvert();
        List<User> list = new ArrayList<>();
        User first = new User(17, "Feofantii", "Bludogorsk");
        User second = new User(25, "Prokop", "Yablonevka");
        User third = new User(39, "Dobrynya", "Zanikitovka");
        list.add(first);
        list.add(second);
        list.add(third);
        HashMap<Integer, User> result = uc.process(list);
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(17, first);
        expect.put(25, second);
        expect.put(39, third);
        assertTrue(result.equals(expect));
    }
}
