package ru.job4j.list;

import org.hamcrest.Matcher;
import org.junit.Test;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleStackTest {

    SimpleStack<Integer> stack = new SimpleStack<>();

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        stack.push(1);
        stack.push(2);
        stack.push(null);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is((Integer) null));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }
}
