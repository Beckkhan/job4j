package ru.job4j.set;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.10.2018
 */
public class SimpleSetTest {
    @Test
    public void whenTryToAddEqualElement() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        simpleSet.add(1);
        simpleSet.add(3);
        simpleSet.add(5);
        simpleSet.add(3);
        assertThat(simpleSet.get(0), is(1));
        assertThat(simpleSet.get(3), is((Integer) null));
    }
}