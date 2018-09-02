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
    @Test
    public void whenSortByNameLenght() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User("Fido", 15));
        users.add(new User("Tornvald", 33));
        users.add(new User("Groom", 29));
        users.add(new User("Silor", 37));
        SortUser.NameLenghtCompare comp = new SortUser.NameLenghtCompare();
        List<User> result = sortUser.sortNameLength(users, comp);
        assertThat(result.iterator().next().getName(), is("Fido"));
        System.out.println(result.toString());
    }
    @Test
    public void whenSortByAllFields() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        User first = new User("Сергей", 25);
        User second = new User("Иван", 30);
        User third = new User("Сергей", 20);
        User fourth = new User("Иван", 25);
        users.add(first);
        users.add(second);
        users.add(third);
        users.add(fourth);
        SortUser.AllFieldsCompare afcomp = new SortUser.AllFieldsCompare();
        List<User> result = sortUser.sortByAllFields(users, afcomp);
        List<User> expect = new ArrayList<>();
        expect.add(fourth);
        expect.add(second);
        expect.add(third);
        expect.add(first);
        System.out.println(result.toString());
        assertThat(result, is(expect));
    }
}