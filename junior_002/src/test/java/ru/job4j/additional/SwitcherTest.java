package ru.job4j.additional;

import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.07.2019
 */
public class SwitcherTest {

    @Test
    public void whenMakeThreeCycles() {
        Switcher sw = new Switcher();
        int repeat = 3;
        Semaphore semaphoreFirst = new Semaphore(1);
        Semaphore semaphoreSecond = new Semaphore(0);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(new FirstThread(sw, semaphoreFirst, semaphoreSecond, repeat));
        ex.submit(new SecondThread(sw, semaphoreFirst, semaphoreSecond, repeat));
        ex.shutdown();
        while (!ex.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertTrue(sw.showSb().equals("111111111122222222221111111111222222222211111111112222222222"));
    }

    @Test
    public void whenHaveNoCycles() {
        Switcher sw = new Switcher();
        int repeat = 0;
        Semaphore semaphoreFirst = new Semaphore(1);
        Semaphore semaphoreSecond = new Semaphore(0);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(new FirstThread(sw, semaphoreFirst, semaphoreSecond, repeat));
        ex.submit(new SecondThread(sw, semaphoreFirst, semaphoreSecond, repeat));
        ex.shutdown();
        while (!ex.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertTrue(sw.showSb().equals(""));
    }
}