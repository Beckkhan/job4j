package ru.job4j.map;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 10.11.2018
 */
public class MyHashMapTest {
    @Test
    public void whenInsertThenDelete() {
        MyHashMap<String, String> store = new MyHashMap<>();
        assertThat(store.insert("Ivan", "Ivanych"), is(true));
        assertThat(store.get("Fedor"), is(nullValue()));
        assertThat(store.get("Ivan"), is("Ivanych"));
        assertThat(store.delete("Makar"), is(false));
        assertThat(store.delete("Ivan"), is(true));
    }
    @Test
    public void whenInsertUserObject() {
        MyHashMap<User, Integer> store = new MyHashMap<>();
        store.insert(new User("Prokop", 5, new GregorianCalendar(1982, 7, 18)), 67);
        assertThat(store.get(new User("Prokop", 5, new GregorianCalendar(1982, 7, 18))), is(67));
    }
    @Test
    public void whenInsertObjectWithSameHashCode() {
        MyHashMap<String, String> store = new MyHashMap<>();
        assertThat(store.insert("Ivan", "Ivanych"), is(true));
        assertThat(store.insert("Ivan", "Ivanov"), is(false));
    }
    @Test
    public void whenTryOperateWithEmptyStore() {
        MyHashMap<String, String> store = new MyHashMap<>();
        assertThat(store.get("Ivan"), is(nullValue()));
        assertThat(store.delete("Ivan"), is(false));
    }
    @Test
    public void iteratorTest() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 3);
        Iterator<MyHashMap.Node> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().value, is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().key, is("second"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().value, is(3));
        assertThat(iterator.hasNext(), is(false));

    }
    @Test (expected = ConcurrentModificationException.class)
    public void whenInsertThenIteratorThrowsConcurrentModificationException() {
        MyHashMap<String, Integer> store = new MyHashMap<>();
        store.insert("first", 1);
        Iterator<MyHashMap.Node> iterator = store.iterator();
        assertThat(iterator.hasNext(), is(true));
        store.insert("second", 2);
        iterator.hasNext();
    }
    @Test (expected = ConcurrentModificationException.class)
    public void whenDeleteThenIteratorThrowsConcurrentModificationException() {
        MyHashMap<String, Integer> store = new MyHashMap<>();
        store.insert("first", 1);
        Iterator<MyHashMap.Node> iterator = store.iterator();
        assertThat(iterator.hasNext(), is(true));
        store.delete("first");
        iterator.hasNext();
    }
    @Test (expected = NoSuchElementException.class)
    public void whenIteratorThrowsNoSuchElementExceptionTest() {
        MyHashMap<String, Integer> store = new MyHashMap<>();
        store.insert("first", 1);
        Iterator<MyHashMap.Node> iterator = store.iterator();
        iterator.next();
        iterator.next();
    }
    @Test
    public void whenStoreIncreasedMoreThanFor16Buckets() {
        MyHashMap<Integer, String> store = new MyHashMap<>();
        store.insert(1, "a");
        store.insert(2, "b");
        store.insert(3, "c");
        store.insert(4, "d");
        store.insert(5, "e");
        store.insert(6, "f");
        store.insert(7, "g");
        store.insert(8, "h");
        store.insert(9, "i");
        store.insert(10, "j");
        store.insert(11, "k");
        store.insert(12, "l");
        store.insert(13, "m");
        store.insert(14, "n");
        store.insert(15, "o");
        store.insert(16, "p");
        store.insert(17, "q");
        assertThat(store.getAmount(), greaterThan(16));
    }
}