package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 14.10.2018
 */
public class SimpleQueueTest {
    private SimpleQueue<Integer> queue;
    @Before
    public void beforeTest() {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }
    @Test
    public void whenPollOnceGotOne() {
        assertThat(queue.poll(), is(1));
    }
    @Test
    public void whenPollThreeTimesThenGotThree() {
        queue.poll();
        queue.poll();
        assertThat(queue.poll(), is(3));
    }
    @Test
    public void whenPollThenPushThenPollTwiceThenGotThree() {
        queue.poll();
        queue.push(4);
        queue.poll();
        assertThat(queue.poll(), is(3));
    }
}