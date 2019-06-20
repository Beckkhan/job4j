package ru.job4j.jmm;

import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 01.12.2018
 */
public class CounterTest {
    @Ignore
    @Test
    public void whenUsingNotSychronizedMethod() throws InterruptedException {
    Counter counter = new Counter();
    for (int i = 0; i < 200; i++) {
        CounterThread ct = new CounterThread(counter);
        ct.start();
    }
        Thread.sleep(2000);
        System.out.println("Counter:" + counter.getCounter());
        assertThat(counter.getCounter(), not(200000));
    }
}