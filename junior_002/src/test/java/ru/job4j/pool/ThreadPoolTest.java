package ru.job4j.pool;

import org.junit.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 05.06.2019
 */
public class ThreadPoolTest {

    ThreadPool pool = new ThreadPool();
    List<Integer> result = new CopyOnWriteArrayList<>();
    List<Integer> expected = List.of(0, 2, 4, 6, 8, 10, 12, 14, 16, 18);
    AtomicInteger count = new AtomicInteger(0);

    Runnable task = () -> {
        int num = count.getAndIncrement();
        result.add(num + num);
    };

    @Test
    public void whenTryToDo10Tasks() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            pool.work(task);
        }
        while (result.size() < 10) {
            Thread.sleep(1000);
        }
        assertThat(result.size(), is(10));
        assertThat(result, is(expected));
    }

    @Test(expected = RejectedExecutionException.class)
    public void whenTryToWorkTaskAfterShutdownThenExc() throws InterruptedException {
        pool.shutdown();
        pool.work(task);
    }
}