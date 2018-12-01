package ru.job4j.jmm;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 01.12.2018
 */
public class CounterSynchroTest {
    @Test
    public void whenUsingNotSychronizedMethod() throws InterruptedException {
        CounterSynchro counterSynchro = new CounterSynchro();
        for (int i = 0; i < 200; i++) {
            CounterThread ct = new CounterThread(counterSynchro);
            ct.start();
        }
        Thread.sleep(2000);
        System.out.println("Counter:" + counterSynchro.getCounter());
        assertThat(counterSynchro.getCounter(), is(200000));
    }
}