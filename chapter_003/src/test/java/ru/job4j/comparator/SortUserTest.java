package ru.job4j.comparator;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.*;
/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 29.08.2018
 */
public class SortUserTest {
    @Test
    public void whenSortUserTest() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User("Fido", 15));
        users.add(new User("Tornvald", 33));
        users.add(new User("Groom", 29));
        users.add(new User("Silor", 37));
        Set<User> result = sortUser.sort(users);
        assertThat(result.iterator().next().getAge(), is(15));
    }
}