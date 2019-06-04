package ru.job4j.pool;

import org.junit.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.06.2019
 */
public class ThreadPoolTest {

    private ThreadPool pool = new ThreadPool();
    private CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    AtomicInteger count = new AtomicInteger(0);

    @Test
    public void whenTryToDo10Tasks() throws InterruptedException {
        Runnable task = () -> {
            int num = count.getAndIncrement();
            list.add(num + num);
        };
        for (int i = 0; i < 10; i++) {
            pool.work(task);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        assertThat(list.size(), is(10));
        assertTrue(list.equals(List.of(0, 2, 4, 6, 8, 10, 12, 14, 16, 18)));
    }
}