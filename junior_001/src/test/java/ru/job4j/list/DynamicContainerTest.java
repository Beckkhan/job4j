package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 17.09.2018
 */
public class DynamicContainerTest {
    DynamicContainer<Integer> values = new DynamicContainer<>();

    @Before
    public void setUp() {
        values.add(1);
        values.add(2);
        values.add(null);
        values.add(4);
        values.add(5);
    }

    @Test
    public void whenIndexIsInBoundOfListThemReturnElemsAtIndex() {
        assertThat(values.get(0), is(5));
        assertThat(values.get(1), is(4));
        assertThat(values.get(2), is((Integer) null));
        assertThat(values.get(3), is(2));
        assertThat(values.get(4), is(1));
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIndexIsOutOfBoundsMustBeException() {
        values.get(5);
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenListModifiedAfterCreatingIteratorMustBeException() {
        Iterator<Integer> integerIterator = values.iterator();
        values.add(7);
        integerIterator.next();
    }
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorWentOutListAndIvokeNextMustBeException() {
        Iterator<Integer> integerIterator = values.iterator();
        for (int i = 0; i < 5; i++) {
            integerIterator.next();
        }
        integerIterator.next();
    }
    @Test
    public void whenTestHasNextAndNextMethods() {
        Iterator<Integer> intIterator = values.iterator();
        assertThat(intIterator.hasNext(), is(true));
        assertThat(intIterator.next(), is(5));
        assertThat(intIterator.hasNext(), is(true));
        assertThat(intIterator.next(), is(4));
        assertThat(intIterator.hasNext(), is(true));
        assertThat(intIterator.next(), is((Integer) null));
        assertThat(intIterator.hasNext(), is(true));
        assertThat(intIterator.next(), is(2));
        assertThat(intIterator.hasNext(), is(true));
        assertThat(intIterator.next(), is(1));
        assertThat(intIterator.hasNext(), is(false));

    }
}
