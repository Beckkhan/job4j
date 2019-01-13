package ru.job4j.nonblockingalgoritm;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 13.01.2019
 */
public class StorageTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }
    @Test
    public void update() {
        Storage st = new Storage();
        st.add(new Base(1, 1));
        st.add(new Base(2, 1));
        st.update(new Base(1, 1));
        assertThat(st.storage.get(1).getVersion(), is(2));
        assertThat(st.storage.get(2).getVersion(), is(1));
        st.update(new Base(2, 1));
        assertThat(st.storage.get(2).getVersion(), is(2));
    }
    @Test (expected = OptimisticException.class)
    public void whenGetOptimisticException() {
        Storage st = new Storage();
        st.add(new Base(1, 1));
        st.update(new Base(1, 1));
        st.update(new Base(1, 2));
        st.update(new Base(1, 2));
    }
}