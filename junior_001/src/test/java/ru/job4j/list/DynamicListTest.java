package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DynamicListTest {
    DynamicList<Integer> container = new DynamicList<>();
    Iterator it;
    @Before
    public void setUp() throws Exception {
        container.add(0);
        container.add(1);
        container.add(2);
        container.add(3);
        it = container.iterator();
    }
    @Test
    public void whenAddThenTake() {
        container = new DynamicList<>();
        for (int i = 0; i < 10; i++) {
            container.add(i);
        }
        assertThat(container.get(7), is(7));
    }
    @Test (expected = ConcurrentModificationException.class)
    public void whenCallNextMethodThenShouldThrowsConcurrentModificationException() {
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        container.add(4);
        assertThat(it.next(), is(2));
    }
    @Test
    public void hasNextAndNextMethodSimpleCheck() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }
    @Test
    public void nextMethodSimpleCheck() {
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }
    @Test
    public void hasNextMethodDoesNotChangeIndexForNextMethod() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test
    public void whenCallNextMethodThenShouldReturnFalseInCaseOfEmptyIterators() {
        container = new DynamicList<>();
        it = container.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCallNextMethodThenShouldThrowsNoSuchElementException() {
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }
}
