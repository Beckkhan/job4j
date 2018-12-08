package ru.job4j.monitor;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 08.12.2018
 */
public class UserStorageTest {

    private final UserStorage store = new UserStorage();

    private class Transfer extends Thread {
        private final UserStorage store;

        private Transfer(final UserStorage store) {
            this.store = store;
        }
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                store.transfer(1, 2, 10);
            }
        }
    }

    @Before
    public void prepare() {
        store.add(new User(1, 700));
        store.add(new User(2, 500));
    }

    @Test
    public void whenTransferWithTenThreads() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Transfer(store)).start();
        }
        Thread.sleep(1000);
        assertThat(store.getUser(1).getAmount(), is(200));
        assertThat(store.getUser(2).getAmount(), is(1000));
    }
    @Test
    public void whenDeleteUser() {
        assertThat(store.delete(new User(1, 700)), is(true));
        assertThat(store.getUser(1), is(nullValue()));
        assertThat(store.delete(new User(1, 700)), is(false));
        assertThat(store.delete(new User(3, 1500)), is(false));
    }
    @Test
    public void whenAddUser() {
        assertThat(store.add(new User(1, 700)), is(false));
        assertThat(store.add(new User(3, 1500)), is(true));
    }

    @Test
    public void whenUpdateStorage() {
        assertThat(store.update(new User(1, 900)), is(true));
        assertThat(store.update(new User(3, 1500)), is(false));
        assertThat(store.update(new User(5, 9000)), is(false));
    }
    @Test
    public void whenTryToTransfer() {
        assertThat(store.transfer(5, 3, 1000), is(false));
        assertThat(store.transfer(1, 2, 3000), is(false));
        assertThat(store.transfer(1, 2, 300), is(true));
        assertThat(store.transfer(1, 2, 500), is(false));
        assertThat(store.transfer(1, 2, 400), is(true));
        assertThat(store.transfer(1, 2, 100), is(false));
        assertThat(store.transfer(2, 1, 600), is(true));
    }
}