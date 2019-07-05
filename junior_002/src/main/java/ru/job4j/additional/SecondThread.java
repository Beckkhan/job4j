package ru.job4j.additional;

import java.util.concurrent.Semaphore;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.07.2019
 */
public class SecondThread implements Runnable {

    Switcher switcher;
    private Semaphore semaphoreFirst;
    private Semaphore semaphoreSecond;
    private int repeat;

    public SecondThread(Switcher switcher, Semaphore semaphoreFirst, Semaphore semaphoreSecond, int repeat) {
        this.switcher = switcher;
        this.semaphoreFirst = semaphoreFirst;
        this.semaphoreSecond = semaphoreSecond;
        this.repeat = repeat;
    }

    @Override
    public void run() {
        int index = 0;
        while (index < repeat) {
            try {
                semaphoreSecond.acquire();
                switcher.add(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphoreFirst.release();
            index++;
        }
    }
}