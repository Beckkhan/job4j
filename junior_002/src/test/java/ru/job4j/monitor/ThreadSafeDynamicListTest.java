package ru.job4j.monitor;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 13.12.2018
 */
public class ThreadSafeDynamicListTest {
    private class DynamicListThread extends Thread {
        private final ThreadSafeDynamicList<Integer> store;

        public DynamicListThread(ThreadSafeDynamicList<Integer> store) {
            this.store = store;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                store.add(1);
            }
        }
    }
    @Test
    public void whenAddThreeThousandThenGetThreeThousand() throws InterruptedException {
        ThreadSafeDynamicList<Integer> store = new ThreadSafeDynamicList<>();
        Thread first = new DynamicListThread(store);
        Thread second = new DynamicListThread(store);
        Thread third = new DynamicListThread(store);
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        int result = 0;
        for (Integer value : store) {
            result += value;
        }
        assertThat(result, is(3000));
    }
}