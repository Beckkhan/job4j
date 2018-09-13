package ru.job4j.generic;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {
    private SimpleArray<Integer> simpleArray;
    @Before
    public void setup() {
        simpleArray = new SimpleArray<Integer>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
    }
    @Test
    public void whenTryToAddAndGet() {
        Assert.assertThat(simpleArray.get(3), Is.is(4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenTryToAddObjectWithIndexOverLenght() {
        simpleArray.add(6);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenTryToGetIndexOutOfBounds() {
        simpleArray.get(7);
    }

    @Test
    public void whenSetNewValue() {
        simpleArray.set(1, 5);
        Assert.assertThat(simpleArray.get(1), Is.is(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenTryToSetNewObjectWithIndexOverLenght() {
        simpleArray.set(7, 3);
    }

    @Test
    public void whenDeleteObjectByFirstIndex() {
        simpleArray.delete(0);
        Assert.assertThat(simpleArray.get(0), Is.is(2));
        Assert.assertThat(simpleArray.get(3), Is.is(5));
        Assert.assertThat(simpleArray.get(4), Is.is((Integer) null));
    }

    @Test
    public void whenDeleteObjectByLastIndex() {
        simpleArray.delete(4);
        Assert.assertThat(simpleArray.get(3), Is.is(4));
        Assert.assertThat(simpleArray.get(4), Is.is((Integer) null));
    }

    @Test
    public void whenDeleteObjectInsideOfSimpleArray() {
        simpleArray.delete(2);
        Assert.assertThat(simpleArray.get(2), Is.is(4));
        Assert.assertThat(simpleArray.get(3), Is.is(5));
        Assert.assertThat(simpleArray.get(4), Is.is((Integer) null));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenTryToCallIteratorForEmptyArray() {
        simpleArray = new SimpleArray<>(1);
        simpleArray.iterator().next();
    }

    @Test
    public void simpleTestForNextAndHasNextMethods() {
        Iterator<Integer> resultIterator = simpleArray.iterator();
        Assert.assertThat(resultIterator.hasNext(), Is.is(true));
        Assert.assertThat(resultIterator.next(), Is.is(1));
        Assert.assertThat(resultIterator.hasNext(), Is.is(true));
        Assert.assertThat(resultIterator.next(), Is.is(2));
        Assert.assertThat(resultIterator.hasNext(), Is.is(true));
        Assert.assertThat(resultIterator.next(), Is.is(3));
        Assert.assertThat(resultIterator.hasNext(), Is.is(true));
        Assert.assertThat(resultIterator.next(), Is.is(4));
        Assert.assertThat(resultIterator.hasNext(), Is.is(true));
        Assert.assertThat(resultIterator.next(), Is.is(5));
        Assert.assertThat(resultIterator.hasNext(), Is.is(false));
    }
    @Test
    public void simpleTestForNextAndHasNextMethodsWithNull() {
        simpleArray.set(0, (Integer) null);
        simpleArray.set(1, (Integer) null);
        simpleArray.set(3, (Integer) null);
        Iterator<Integer> resultIterator = simpleArray.iterator();
        Assert.assertThat(resultIterator.hasNext(), Is.is(true));
        Assert.assertThat(resultIterator.next(), Is.is(3));
        Assert.assertThat(resultIterator.hasNext(), Is.is(true));
        Assert.assertThat(resultIterator.next(), Is.is(5));
        Assert.assertThat(resultIterator.hasNext(), Is.is(false));
    }
}
