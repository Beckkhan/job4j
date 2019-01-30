package ru.job4j.comparator;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.*;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 3.0
 * @since 30.01.2019
 */
public class SortUserTest {
    SortUser sortUser = new SortUser();
    User first = new User("Fido", 15);
    User second = new User("Tornvald", 33);
    User third = new User("Groom", 29);
    User fourth = new User("Silor", 37);
    List<User> users = List.of(first, second, third, fourth);
    @Test
    public void whenSortUserTest() {
        Set<User> result = sortUser.sort(users);
        assertThat(result.iterator().next().getAge(), is(15));
    }
    @Test
    public void whenSortByNameLenght() {
        SortUser.NameLenghtCompare comp = new SortUser.NameLenghtCompare();
        List<User> result = sortUser.sortNameLength(users, comp);
        assertThat(result.iterator().next().getName(), is("Fido"));
    }
    @Test
    public void whenSortByAllFields() {
        SortUser.AllFieldsCompare afcomp = new SortUser.AllFieldsCompare();
        List<User> result = sortUser.sortByAllFields(users, afcomp);
        List<User> expect = List.of(first, third, fourth, second);
        assertThat(result, is(expect));
    }
}