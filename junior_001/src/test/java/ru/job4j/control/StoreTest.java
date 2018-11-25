package ru.job4j.control;

import ru.job4j.control.Store.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 25.11.2018
 */
public class StoreTest {
    List<Store.User> previous;
    List<Store.User> current;
    Store store;

    @Before
    public void prepare() {
        previous = new ArrayList<>();
        previous.add(new User(1, "First"));
        previous.add(new User(2, "Second"));
        previous.add(new User(3, "Third"));
        previous.add(new User(4, "Fourth"));
        previous.add(new User(5, "Fifth"));
        current = new ArrayList<>(previous);
        store = new Store();
    }
    @Test
    public void whenJustCompareWithoutChanges() {
        Info info = store.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }
    @Test
    public void whenOnlyAddThenGetInfo() {
        current.add(new User(6, "Six"));
        current.add(new User(7, "Seven"));
        current.add(new User(8, "Eight"));
        current.add(new User(9, "Nine"));
        current.add(new User(10, "Ten"));
        Info info = store.diff(previous, current);
        assertThat(info.getAdded(), is(5));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }
    @Test
    public void whenDeleteAllThenGetInfo() {
        current.clear();
        Info info = store.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(5));
    }
    @Test
    public void whenOneDeleteTwoChangedThreeAddedThenGetInfo() {
        current.remove(4);
        current.set(1, new User(2, "TheFirst"));
        current.set(2, new User(3, "TheSecond"));
        current.add(new User(6, "Sixth"));
        current.add(new User(7, "Seventh"));
        current.add(new User(8, "Eighth"));
        Info info = store.diff(previous, current);
        assertThat(info.getAdded(), is(3));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(1));
    }
    @Test
    public void whenChangedAllThenGetInfo() {
        current.set(0, new User(1, "OneOne"));
        current.set(1, new User(2, "TwoTwo"));
        current.set(2, new User(3, "ThreeThree"));
        current.set(3, new User(4, "FourFour"));
        current.set(4, new User(5, "FiveFive"));
        Info info = store.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(5));
        assertThat(info.getDeleted(), is(0));
    }
}
